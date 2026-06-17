package com.example.pizzaria.Dominio.Servicos.Impostos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImpostoLei12345Test {

    private final ImpostoLei12345 imposto = new ImpostoLei12345();

    @Test
    void deveRetornarCodigoCorreto() {
        assertEquals("Lei12345", imposto.getCodigo());
    }

    @Test
    void deveCalcular10PorCentoDeImposto() {
        assertEquals(1000.0, imposto.calcularImposto(10000.0), 0.01);
    }

    @Test
    void deveRetornarZeroParaValorZero() {
        assertEquals(0.0, imposto.calcularImposto(0.0), 0.01);
    }

    @Test
    void deveCalcularImpostoParaValorPequeno() {
        assertEquals(0.50, imposto.calcularImposto(5.0), 0.01);
    }
}
