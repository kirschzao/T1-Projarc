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
import com.example.pizzaria.Dominio.Servicos.IDescontoService;
import com.example.pizzaria.Dominio.Servicos.IEstoqueService;
import com.example.pizzaria.Dominio.Servicos.IImpostoService;
import com.example.pizzaria.Dominio.Servicos.IProdutoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SubmeterPedidoUC {
    private final IProdutoService produtoService;
    private final IEstoqueService estoqueService;
    private final IImpostoService impostoService;
    private final IDescontoService descontoService;

    public PedidoResponse run(SubmeterPedidoRequest request) {
        List<ItemPedido> itensDoPedido = new ArrayList<>();

        // 1. Instanciar os itens do domínio
        for (ItemPedidoRequest reqItem : request.itens()) {
            Produto produto = produtoService.recuperaProdutoPorId(reqItem.produtoId());
            if (produto == null) {
                return new PedidoResponse("NEGADO", 0, 0, 0, 0, "Produto ID " + reqItem.produtoId() + " não encontrado.");
            }
            itensDoPedido.add(new ItemPedido(produto, reqItem.quantidade()));
        }

        // 2. Verificação de Estoque
        if (!estoqueService.verificaDisponibilidade(itensDoPedido)) {
            return new PedidoResponse("NEGADO", 0, 0, 0, 0, "Estoque insuficiente para um ou mais ingredientes.");
        }

        // 3. Cálculos Financeiros
        double valorTotalItens = itensDoPedido.stream()
                .mapToDouble(item -> item.getItem().getPreco() * item.getQuantidade())
                .sum();

        double desconto = descontoService.calcularDesconto(request.emailCliente(), valorTotalItens);
        double imposto = impostoService.calcularImposto(valorTotalItens);
        
        double valorCobrado = (valorTotalItens - desconto) + imposto;

        Cliente clienteRef = new Cliente("N/A", "N/A", "N/A", request.enderecoEntrega(), request.emailCliente());
        
        Pedido pedido = new Pedido(
            0, 
            clienteRef, 
            null, 
            itensDoPedido, 
            Pedido.Status.APROVADO, 
            valorTotalItens, 
            imposto, 
            desconto, 
            valorCobrado
        );

        return new PedidoResponse(
            pedido.getStatus().name(),
            pedido.getValor(),
            pedido.getImpostos(),
            pedido.getDesconto(),
            pedido.getValorCobrado(),
            "Pedido aprovado com sucesso. Aguardando pagamento."
        );
    }
}