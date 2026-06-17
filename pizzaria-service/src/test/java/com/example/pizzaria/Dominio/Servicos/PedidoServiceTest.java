package com.example.pizzaria.Dominio.Servicos;

import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Entidades.*;
import com.example.pizzaria.Dominio.Exceptions.AcessoNegadoException;
import com.example.pizzaria.Dominio.Exceptions.RegraDeNegocioException;
import com.example.pizzaria.Dominio.Servicos.Descontos.DescontoManager;
import com.example.pizzaria.Dominio.Servicos.Descontos.IDescontoService;
import com.example.pizzaria.Dominio.Servicos.Impostos.IImpostoService;
import com.example.pizzaria.Dominio.Servicos.Impostos.ImpostoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock private PedidoRepository pedidoRepository;
    @Mock private ProdutoService produtoService;
    @Mock private IEstoqueService estoqueService;
    @Mock private ImpostoFactory impostoFactory;
    @Mock private DescontoManager descontoManager;

    @InjectMocks
    private PedidoService pedidoService;

    private Cliente clienteTest() {
        return new Cliente("12345678900", "João", "51999999999",
                "Rua A, 123", "joao@email.com", "hash", "CLIENTE");
    }

    private Produto produtoTest(long id, int preco) {
        Receita receita = new Receita(id, "Receita " + id, List.of(new Ingrediente(1L, "Farinha")));
        return new Produto(id, "Produto " + id, receita, preco);
    }

    @Test
    void deveValidarECriarItensComProdutosDisponiveis() {
        when(produtoService.recuperaProdutoPorId(1L)).thenReturn(produtoTest(1L, 5000));
        when(produtoService.recuperaProdutoPorId(2L)).thenReturn(produtoTest(2L, 3000));

        List<ItemPedido> itens = pedidoService.validarECriarItens(
                List.of(1L, 2L), List.of(2, 1));

        assertEquals(2, itens.size());
        assertEquals(2, itens.get(0).getQuantidade());
        assertEquals(1, itens.get(1).getQuantidade());
    }

    @Test
    void deveLancarExcecaoQuandoProdutoIndisponivel() {
        Produto indisponivel = new Produto(1L, "Pizza Indisponivel",
                new Receita(1L, "R", List.of(new Ingrediente(1L, "I"))), 5000, false);
        when(produtoService.recuperaProdutoPorId(1L)).thenReturn(indisponivel);

        assertThrows(RegraDeNegocioException.class,
                () -> pedidoService.validarECriarItens(List.of(1L), List.of(1)));
    }

    @Test
    void deveCriarPedidoComCalculoDeValor() {
        Cliente cliente = clienteTest();
        Produto p1 = produtoTest(1L, 5000);
        List<ItemPedido> itens = List.of(new ItemPedido(p1, 2));

        IImpostoService impostoService = mock(IImpostoService.class);
        IDescontoService descontoService = mock(IDescontoService.class);
        when(impostoFactory.getAtivo()).thenReturn(impostoService);
        when(descontoManager.getAtivo()).thenReturn(descontoService);
        when(impostoService.calcularImposto(10000.0)).thenReturn(1000.0);
        when(descontoService.calcularDesconto("joao@email.com", 10000.0)).thenReturn(0.0);
        when(pedidoRepository.salvar(any(Pedido.class))).thenAnswer(inv -> {
            Pedido p = inv.getArgument(0);
            return new Pedido(1L, p.getCliente(), p.getItens(), p.getStatus(),
                    p.getValor(), p.getImpostos(), p.getDesconto(), p.getValorCobrado(), p.getDataCriacao());
        });

        Pedido pedido = pedidoService.criarPedido(cliente, itens, "Rua B, 456");

        assertEquals(10000.0, pedido.getValor(), 0.01);
        assertEquals(1000.0, pedido.getImpostos(), 0.01);
        assertEquals(0.0, pedido.getDesconto(), 0.01);
        assertEquals(11000.0, pedido.getValorCobrado(), 0.01);
    }

    @Test
    void deveAprovarPedidoQuandoEstoqueDisponivel() {
        Produto p1 = produtoTest(1L, 5000);
        List<ItemPedido> itens = List.of(new ItemPedido(p1, 1));
        Pedido pedido = new Pedido(1L, clienteTest(), itens, Pedido.Status.NOVO,
                5000, 500, 0, 5500, LocalDateTime.now());

        when(estoqueService.verificaDisponibilidade(itens)).thenReturn(true);

        String erro = pedidoService.verificarEstoqueEAprovar(pedido, itens);

        assertNull(erro);
        assertEquals(Pedido.Status.APROVADO, pedido.getStatus());
        verify(estoqueService).darBaixa(itens);
        verify(pedidoRepository).atualizar(pedido);
    }

    @Test
    void deveRetornarErroQuandoEstoqueInsuficiente() {
        Produto p1 = produtoTest(1L, 5000);
        List<ItemPedido> itens = List.of(new ItemPedido(p1, 1));
        Pedido pedido = new Pedido(1L, clienteTest(), itens, Pedido.Status.NOVO,
                5000, 500, 0, 5500, LocalDateTime.now());

        when(estoqueService.verificaDisponibilidade(itens)).thenReturn(false);
        when(estoqueService.identificarProdutosIndisponiveis(itens)).thenReturn(List.of(1L));

        String erro = pedidoService.verificarEstoqueEAprovar(pedido, itens);

        assertNotNull(erro);
        assertTrue(erro.contains("Produto 1"));
        verify(produtoService).marcarComoIndisponivel(1L);
    }

    @Test
    void deveValidarPropriedadeDoPedido() {
        Pedido pedido = new Pedido(1L, clienteTest(), List.of(), Pedido.Status.APROVADO,
                0, 0, 0, 0, LocalDateTime.now());

        assertDoesNotThrow(() -> pedidoService.validarPropriedade(pedido, "joao@email.com"));
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoPertenceAoCliente() {
        Pedido pedido = new Pedido(1L, clienteTest(), List.of(), Pedido.Status.APROVADO,
                0, 0, 0, 0, LocalDateTime.now());

        assertThrows(AcessoNegadoException.class,
                () -> pedidoService.validarPropriedade(pedido, "outro@email.com"));
    }

    @Test
    void deveCancelarPedidoAprovado() {
        Pedido pedido = new Pedido(1L, clienteTest(), List.of(), Pedido.Status.APROVADO,
                5000, 500, 0, 5500, LocalDateTime.now());

        pedidoService.cancelar(pedido);

        verify(pedidoRepository).atualizar(argThat(p -> p.getStatus() == Pedido.Status.CANCELADO));
    }

    @Test
    void deveLancarExcecaoAoCancelarPedidoNaoAprovado() {
        Pedido pedido = new Pedido(1L, clienteTest(), List.of(), Pedido.Status.PAGO,
                5000, 500, 0, 5500, LocalDateTime.now());

        assertThrows(RegraDeNegocioException.class, () -> pedidoService.cancelar(pedido));
    }
}
