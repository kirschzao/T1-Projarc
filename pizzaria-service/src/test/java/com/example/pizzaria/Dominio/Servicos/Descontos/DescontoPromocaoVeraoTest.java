package com.example.pizzaria.Dominio.Servicos.Descontos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DescontoPromocaoVeraoTest {

    private final DescontoPromocaoVerao desconto = new DescontoPromocaoVerao();

    @Test
    void deveRetornarCodigoCorreto() {
        assertEquals("PromocaoVerao", desconto.getCodigo());
    }

    @Test
    void deveRetornarDescricao() {
        assertNotNull(desconto.getDescricao());
        assertFalse(desconto.getDescricao().isBlank());
    }

    @Test
    void deveCalcular10PorCentoDeDesconto() {
        double resultado = desconto.calcularDesconto("cliente@email.com", 10000.0);
        assertEquals(1000.0, resultado, 0.01);
    }

    @Test
    void deveRetornarZeroParaValorZero() {
        double resultado = desconto.calcularDesconto("cliente@email.com", 0.0);
        assertEquals(0.0, resultado, 0.01);
    }

    @Test
    void deveCalcularDescontoIndependenteDoEmail() {
        double r1 = desconto.calcularDesconto("a@a.com", 5000.0);
        double r2 = desconto.calcularDesconto("b@b.com", 5000.0);
        assertEquals(r1, r2, 0.01);
    }
}
