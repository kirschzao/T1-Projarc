package com.example.pizzaria.Aplicacao;

import com.example.pizzaria.Aplicacao.Requests.ItemPedidoRequest;
import com.example.pizzaria.Aplicacao.Requests.SubmeterPedidoRequest;
import com.example.pizzaria.Aplicacao.Responses.PedidoResponse;
import com.example.pizzaria.Dominio.Entidades.*;
import com.example.pizzaria.Dominio.Servicos.ClienteService;
import com.example.pizzaria.Dominio.Servicos.PedidoService;
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
class SubmeterPedidoUCTest {

    @Mock private PedidoService pedidoService;
    @Mock private ClienteService clienteService;

    @InjectMocks
    private SubmeterPedidoUC submeterPedidoUC;

    private Cliente clienteTest() {
        return new Cliente("12345678900", "João", "51999999999",
                "Rua A", "joao@email.com", "hash", "CLIENTE");
    }

    @Test
    void deveSubmeterPedidoComSucesso() {
        Cliente cliente = clienteTest();
        Receita receita = new Receita(1L, "R1", List.of(new Ingrediente(1L, "Farinha")));
        Produto produto = new Produto(1L, "Pizza", receita, 5000);
        List<ItemPedido> itens = List.of(new ItemPedido(produto, 2));
        Pedido pedido = new Pedido(1L, cliente, itens, Pedido.Status.APROVADO,
                10000, 1000, 0, 11000, LocalDateTime.now());

        when(clienteService.recuperarPorEmail("joao@email.com")).thenReturn(cliente);
        when(pedidoService.validarECriarItens(List.of(1L), List.of(2))).thenReturn(itens);
        when(pedidoService.criarPedido(eq(cliente), eq(itens), eq("Rua B, 456"))).thenReturn(pedido);
        when(pedidoService.verificarEstoqueEAprovar(pedido, itens)).thenReturn(null);

        SubmeterPedidoRequest request = new SubmeterPedidoRequest(
                "Rua B, 456", List.of(new ItemPedidoRequest(1L, 2)));

        PedidoResponse response = submeterPedidoUC.run("joao@email.com", request);

        assertEquals("APROVADO", response.status());
        assertEquals(1L, response.pedidoId());
        assertEquals(10000, response.valorTotal());
    }

    @Test
    void deveRetornarNegadoQuandoEstoqueInsuficiente() {
        Cliente cliente = clienteTest();
        Receita receita = new Receita(1L, "R1", List.of(new Ingrediente(1L, "Farinha")));
        Produto produto = new Produto(1L, "Pizza", receita, 5000);
        List<ItemPedido> itens = List.of(new ItemPedido(produto, 2));
        Pedido pedido = new Pedido(1L, cliente, itens, Pedido.Status.NOVO,
                10000, 1000, 0, 11000, LocalDateTime.now());

        when(clienteService.recuperarPorEmail("joao@email.com")).thenReturn(cliente);
        when(pedidoService.validarECriarItens(List.of(1L), List.of(2))).thenReturn(itens);
        when(pedidoService.criarPedido(eq(cliente), eq(itens), eq("Rua B, 456"))).thenReturn(pedido);
        when(pedidoService.verificarEstoqueEAprovar(pedido, itens))
                .thenReturn("Estoque insuficiente. Itens não atendidos: Pizza");

        SubmeterPedidoRequest request = new SubmeterPedidoRequest(
                "Rua B, 456", List.of(new ItemPedidoRequest(1L, 2)));

        PedidoResponse response = submeterPedidoUC.run("joao@email.com", request);

        assertEquals("NEGADO", response.status());
        assertEquals(0, response.valorTotal());
    }
}
