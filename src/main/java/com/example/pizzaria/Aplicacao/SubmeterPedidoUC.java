package com.example.pizzaria.Aplicacao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailAutenticado = authentication != null ? authentication.getName() : null;
        if (emailAutenticado == null || emailAutenticado.isBlank() || "anonymousUser".equals(emailAutenticado)) {
            return new PedidoResponse(0, "NEGADO", 0, 0, 0, 0, "Usuário não autenticado.");
        }

        // 0. Recuperar cliente por email
        Cliente cliente = clienteService.recuperarPorEmail(emailAutenticado);
        if (cliente == null) {
            return new PedidoResponse(0, "NEGADO", 0, 0, 0, 0, "Cliente não encontrado. Por favor, cadastre-se antes de fazer um pedido.");
        }

        // 1. Instanciar os itens do domínio
        Set<Long> itensJaIndisponiveis = new LinkedHashSet<>();
        for (ItemPedidoRequest reqItem : request.itens()) {
            Produto produto = produtoService.recuperaProdutoPorId(reqItem.produtoId());
            if (produto == null) {
                return new PedidoResponse(0, "NEGADO", 0, 0, 0, 0, "Produto ID " + reqItem.produtoId() + " não encontrado.");
            }
            if (!produto.isDisponivel()) {
                itensJaIndisponiveis.add(produto.getId());
            }
            itensDoPedido.add(new ItemPedido(produto, reqItem.quantidade()));
        }

        if (!itensJaIndisponiveis.isEmpty()) {
            String itensIndisponiveis = itensDoPedido.stream()
                .map(ItemPedido::getItem)
                .filter(produto -> itensJaIndisponiveis.contains(produto.getId()))
                .map(Produto::getDescricao)
                .distinct()
                .collect(Collectors.joining(", "));

            return new PedidoResponse(0, "NEGADO", 0, 0, 0, 0,
                "Pedido contém itens indisponíveis no cardápio: " + itensIndisponiveis + ".");
        }

        // 2. Cálculos financeiros (pedido é criado inicialmente como NOVO)
        double valorTotalItens = itensDoPedido.stream()
                .mapToDouble(item -> item.getItem().getPreco() * item.getQuantidade())
                .sum();

        double desconto = descontoService.calcularDesconto(emailAutenticado, valorTotalItens);
        double imposto = impostoService.calcularImposto(valorTotalItens);
        
        double valorCobrado = (valorTotalItens - desconto) + imposto;

        // Criar novo cliente com endereço de entrega atualizado
        Cliente clienteComEndereco = new Cliente(
            cliente.getCpf(),
            cliente.getNome(),
            cliente.getCelular(),
            request.enderecoEntrega(),
            cliente.getEmail(),
            cliente.getSenha()
        );
        
        Pedido pedido = new Pedido(
            0, 
            clienteComEndereco,
            itensDoPedido, 
            Pedido.Status.NOVO, 
            valorTotalItens, 
            imposto, 
            desconto, 
            valorCobrado
        );

        // 4. Persistir o pedido
        Pedido pedidoSalvo = pedidoService.salvar(pedido);

        // 5. Verificação de estoque após criação do pedido NOVO
        if (!estoqueService.verificaDisponibilidade(itensDoPedido)) {
            List<Long> produtosIndisponiveis = estoqueService.identificarProdutosIndisponiveis(itensDoPedido);
            for (Long produtoId : produtosIndisponiveis) {
                produtoService.marcarComoIndisponivel(produtoId);
            }

            String itensNaoAtendidos = itensDoPedido.stream()
                .map(ItemPedido::getItem)
                .filter(produto -> produtosIndisponiveis.contains(produto.getId()))
                .map(Produto::getDescricao)
                .distinct()
                .collect(Collectors.joining(", "));

            String mensagem = itensNaoAtendidos.isBlank()
                ? "Estoque insuficiente para um ou mais ingredientes."
                : "Estoque insuficiente. Itens não atendidos: " + itensNaoAtendidos + ". Itens foram marcados como indisponíveis.";

            return new PedidoResponse(
                pedidoSalvo.getId(),
                "NEGADO",
                0,
                0,
                0,
                0,
                mensagem
            );
        }

        // 6. Com estoque OK, pedido passa para APROVADO
        pedidoSalvo.setStatus(Pedido.Status.APROVADO);
        pedidoService.atualizar(pedidoSalvo);

        return new PedidoResponse(
            pedidoSalvo.getId(),
            Pedido.Status.APROVADO.name(),
            pedidoSalvo.getValor(),
            pedidoSalvo.getImpostos(),
            pedidoSalvo.getDesconto(),
            pedidoSalvo.getValorCobrado(),
            "Pedido aprovado com sucesso. Aguardando pagamento."
        );
    }
}