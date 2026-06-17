package com.example.pizzaria.Dominio.Servicos.Impostos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImpostoLei67890Test {

    private final ImpostoLei67890 imposto = new ImpostoLei67890();

    @Test
    void deveRetornarCodigoCorreto() {
        assertEquals("Lei67890", imposto.getCodigo());
    }

    @Test
    void deveCalcular8PorCentoParaValoresAte10000() {
        assertEquals(800.0, imposto.calcularImposto(10000.0), 0.01);
        assertEquals(400.0, imposto.calcularImposto(5000.0), 0.01);
    }

    @Test
    void deveCalcular12PorCentoParaValoresAcimaDe10000() {
        assertEquals(1200.12, imposto.calcularImposto(10001.0), 0.01);
        assertEquals(2400.0, imposto.calcularImposto(20000.0), 0.01);
    }

    @Test
    void deveRetornarZeroParaValorZero() {
        assertEquals(0.0, imposto.calcularImposto(0.0), 0.01);
    }
}
