package com.example.pizzaria.Dominio.Servicos.Descontos;

import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Servicos.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DescontoFidelidadeTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private DescontoFidelidade descontoFidelidade;

    @Test
    void deveRetornarCodigoCorreto() {
        assertEquals("Fidelidade", descontoFidelidade.getCodigo());
    }

    @Test
    void deveRetornarDescricao() {
        assertNotNull(descontoFidelidade.getDescricao());
    }

    @Test
    void deveAplicar7PorCentoParaClienteComMaisDe3Pedidos() {
        Cliente cliente = new Cliente("12345678900", "João", "51999999999",
                "Rua A", "joao@email.com", "hash", "CLIENTE");
        when(clienteService.recuperarPorEmail("joao@email.com")).thenReturn(cliente);
        when(pedidoRepository.contarPedidosPorClienteNoPeriodo("12345678900", 20)).thenReturn(4);

        double desconto = descontoFidelidade.calcularDesconto("joao@email.com", 10000.0);

        assertEquals(700.0, desconto, 0.01);
    }

    @Test
    void deveRetornarZeroParaClienteComAte3Pedidos() {
        Cliente cliente = new Cliente("12345678900", "Maria", "51888888888",
                "Rua B", "maria@email.com", "hash", "CLIENTE");
        when(clienteService.recuperarPorEmail("maria@email.com")).thenReturn(cliente);
        when(pedidoRepository.contarPedidosPorClienteNoPeriodo("12345678900", 20)).thenReturn(3);

        double desconto = descontoFidelidade.calcularDesconto("maria@email.com", 10000.0);

        assertEquals(0.0, desconto, 0.01);
    }

    @Test
    void deveRetornarZeroParaClienteSemPedidos() {
        Cliente cliente = new Cliente("11111111111", "Novo", "51777777777",
                "Rua C", "novo@email.com", "hash", "CLIENTE");
        when(clienteService.recuperarPorEmail("novo@email.com")).thenReturn(cliente);
        when(pedidoRepository.contarPedidosPorClienteNoPeriodo("11111111111", 20)).thenReturn(0);

        double desconto = descontoFidelidade.calcularDesconto("novo@email.com", 10000.0);

        assertEquals(0.0, desconto, 0.01);
    }
}
