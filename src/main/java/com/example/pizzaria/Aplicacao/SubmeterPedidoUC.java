package com.example.pizzaria.Aplicacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Requests.ItemPedidoRequest;
import com.example.pizzaria.Aplicacao.Requests.SubmeterPedidoRequest;
import com.example.pizzaria.Aplicacao.Responses.PedidoResponse;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Entidades.ItemPedido;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Entidades.Produto;
import com.example.pizzaria.Dominio.Servicos.IClienteService;
import com.example.pizzaria.Dominio.Servicos.IDescontoService;
import com.example.pizzaria.Dominio.Servicos.IEstoqueService;
import com.example.pizzaria.Dominio.Servicos.IImpostoService;
import com.example.pizzaria.Dominio.Servicos.IPedidoService;
import com.example.pizzaria.Dominio.Servicos.IProdutoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SubmeterPedidoUC {
    private final IProdutoService produtoService;
    private final IEstoqueService estoqueService;
    private final IImpostoService impostoService;
    private final IDescontoService descontoService;
    private final IPedidoService pedidoService;
    private final IClienteService clienteService;

    public PedidoResponse run(SubmeterPedidoRequest request) {
        List<ItemPedido> itensDoPedido = new ArrayList<>();

        // 0. Recuperar cliente por email
        Cliente cliente = clienteService.recuperarPorEmail(request.emailCliente());
        if (cliente == null) {
            return new PedidoResponse(0, "NEGADO", 0, 0, 0, 0, "Cliente não encontrado. Por favor, cadastre-se antes de fazer um pedido.");
        }

        // 1. Instanciar os itens do domínio
        for (ItemPedidoRequest reqItem : request.itens()) {
            Produto produto = produtoService.recuperaProdutoPorId(reqItem.produtoId());
            if (produto == null) {
                return new PedidoResponse(0, "NEGADO", 0, 0, 0, 0, "Produto ID " + reqItem.produtoId() + " não encontrado.");
            }
            itensDoPedido.add(new ItemPedido(produto, reqItem.quantidade()));
        }

        // 2. Verificação de Estoque
        if (!estoqueService.verificaDisponibilidade(itensDoPedido)) {
            return new PedidoResponse(0, "NEGADO", 0, 0, 0, 0, "Estoque insuficiente para um ou mais ingredientes.");
        }

        // 3. Cálculos Financeiros
        double valorTotalItens = itensDoPedido.stream()
                .mapToDouble(item -> item.getItem().getPreco() * item.getQuantidade())
                .sum();

        double desconto = descontoService.calcularDesconto(request.emailCliente(), valorTotalItens);
        double imposto = impostoService.calcularImposto(valorTotalItens);
        
        double valorCobrado = (valorTotalItens - desconto) + imposto;

        // Criar novo cliente com endereço de entrega atualizado
        Cliente clienteComEndereco = new Cliente(
            cliente.getCpf(),
            cliente.getNome(),
            cliente.getCelular(),
            request.enderecoEntrega(),
            cliente.getEmail()
        );
        
        Pedido pedido = new Pedido(
            0, 
            clienteComEndereco,
            itensDoPedido, 
            Pedido.Status.APROVADO, 
            valorTotalItens, 
            imposto, 
            desconto, 
            valorCobrado
        );

        // 4. Persistir o pedido
        Pedido pedidoSalvo = pedidoService.salvar(pedido);

        return new PedidoResponse(
            pedidoSalvo.getId(),
            pedidoSalvo.getStatus().name(),
            pedidoSalvo.getValor(),
            pedidoSalvo.getImpostos(),
            pedidoSalvo.getDesconto(),
            pedidoSalvo.getValorCobrado(),
            "Pedido aprovado com sucesso. Aguardando pagamento."
        );
    }
}