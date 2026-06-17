package com.example.pizzaria.Dominio.Servicos.Descontos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DescontoPromocaoDiaDosPaisTest {

    private final DescontoPromocaoDiaDosPais desconto = new DescontoPromocaoDiaDosPais();

    @Test
    void deveRetornarCodigoCorreto() {
        assertEquals("PromocaoDiaDosPais", desconto.getCodigo());
    }

    @Test
    void deveRetornarDescricao() {
        assertNotNull(desconto.getDescricao());
        assertFalse(desconto.getDescricao().isBlank());
    }

    @Test
    void deveAplicar15PorCentoParaPedidosAcimaDeR80() {
        double resultado = desconto.calcularDesconto("cliente@email.com", 10000.0);
        assertEquals(1500.0, resultado, 0.01);
    }

    @Test
    void deveRetornarZeroParaPedidosAteR80() {
        double resultado = desconto.calcularDesconto("cliente@email.com", 8000.0);
        assertEquals(0.0, resultado, 0.01);
    }

    @Test
    void deveRetornarZeroParaPedidosPequenos() {
        double resultado = desconto.calcularDesconto("cliente@email.com", 5000.0);
        assertEquals(0.0, resultado, 0.01);
    }
}
