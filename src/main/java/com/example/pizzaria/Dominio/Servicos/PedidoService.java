package com.example.pizzaria.Dominio.Servicos;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Entidades.ItemPedido;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Entidades.Produto;
import com.example.pizzaria.Dominio.Exceptions.AcessoNegadoException;
import com.example.pizzaria.Dominio.Exceptions.RecursoNaoEncontradoException;
import com.example.pizzaria.Dominio.Exceptions.RegraDeNegocioException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final IEstoqueService estoqueService;
    private final ImpostoService impostoService;
    private final DescontoService descontoService;

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.salvar(pedido);
    }

    public Pedido recuperarPorId(long id) {
        Pedido pedido = pedidoRepository.recuperarPorId(id);
        if (pedido == null) {
            throw new RecursoNaoEncontradoException("Pedido ID " + id + " não encontrado.");
        }
        return pedido;
    }

    public void atualizar(Pedido pedido) {
        pedidoRepository.atualizar(pedido);
    }

    public List<ItemPedido> validarECriarItens(List<Long> produtoIds, List<Integer> quantidades) {
        List<ItemPedido> itensDoPedido = new ArrayList<>();
        Set<Long> itensIndisponiveis = new LinkedHashSet<>();

        for (int i = 0; i < produtoIds.size(); i++) {
            Produto produto = produtoService.recuperaProdutoPorId(produtoIds.get(i));
            if (!produto.isDisponivel()) {
                itensIndisponiveis.add(produto.getId());
            }
            itensDoPedido.add(new ItemPedido(produto, quantidades.get(i)));
        }

        if (!itensIndisponiveis.isEmpty()) {
            String nomes = itensDoPedido.stream()
                    .map(ItemPedido::getItem)
                    .filter(p -> itensIndisponiveis.contains(p.getId()))
                    .map(Produto::getDescricao)
                    .distinct()
                    .collect(Collectors.joining(", "));
            throw new RegraDeNegocioException("Pedido contém itens indisponíveis no cardápio: " + nomes + ".");
        }

        return itensDoPedido;
    }

    public Pedido criarPedido(Cliente cliente, List<ItemPedido> itens, String enderecoEntrega) {
        double valorTotal = calcularValorTotal(itens);
        double desconto = descontoService.calcularDesconto(cliente.getEmail(), valorTotal);
        double imposto = impostoService.calcularImposto(valorTotal);
        double valorCobrado = (valorTotal - desconto) + imposto;

        Cliente clienteComEndereco = new Cliente(
                cliente.getCpf(), cliente.getNome(), cliente.getCelular(),
                enderecoEntrega, cliente.getEmail(), cliente.getSenha());

        Pedido pedido = new Pedido(0, clienteComEndereco, itens, Pedido.Status.NOVO,
                valorTotal, imposto, desconto, valorCobrado, java.time.LocalDateTime.now());

        return pedidoRepository.salvar(pedido);
    }

    public String verificarEstoqueEAprovar(Pedido pedido, List<ItemPedido> itens) {
        if (!estoqueService.verificaDisponibilidade(itens)) {
            List<Long> indisponiveis = estoqueService.identificarProdutosIndisponiveis(itens);
            for (Long produtoId : indisponiveis) {
                produtoService.marcarComoIndisponivel(produtoId);
            }

            String nomes = itens.stream()
                    .map(ItemPedido::getItem)
                    .filter(p -> indisponiveis.contains(p.getId()))
                    .map(Produto::getDescricao)
                    .distinct()
                    .collect(Collectors.joining(", "));

            return nomes.isBlank()
                    ? "Estoque insuficiente para um ou mais ingredientes."
                    : "Estoque insuficiente. Itens não atendidos: " + nomes
                            + ". Itens foram marcados como indisponíveis.";
        }

        pedido.setStatus(Pedido.Status.APROVADO);
        pedidoRepository.atualizar(pedido);
        return null;
    }

    public void validarPropriedade(Pedido pedido, String emailCliente) {
        if (!pedido.getCliente().getEmail().equals(emailCliente)) {
            throw new AcessoNegadoException("Acesso negado. Este pedido não pertence ao usuário autenticado.");
        }
    }

    public void cancelar(Pedido pedido) {
        if (pedido.getStatus() != Pedido.Status.APROVADO) {
            throw new RegraDeNegocioException(
                    "Cancelamento negado. Pedido não está em estado APROVADO. Estado atual: "
                            + pedido.getStatus().name());
        }
        Pedido pedidoCancelado = new Pedido(
                pedido.getId(), pedido.getCliente(), pedido.getItens(),
                Pedido.Status.CANCELADO, pedido.getValor(), pedido.getImpostos(),
                pedido.getDesconto(), pedido.getValorCobrado(), pedido.getDataCriacao());
        pedidoRepository.atualizar(pedidoCancelado);
    }

    private double calcularValorTotal(List<ItemPedido> itens) {
        return itens.stream()
                .mapToDouble(item -> item.getItem().getPreco() * item.getQuantidade())
                .sum();
    }
}
