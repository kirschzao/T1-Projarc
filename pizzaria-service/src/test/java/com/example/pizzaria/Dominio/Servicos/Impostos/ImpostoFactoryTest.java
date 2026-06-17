package com.example.pizzaria.Dominio.Servicos.Impostos;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ImpostoFactoryTest {

    @Test
    void deveRetornarEstrategiaAtiva() {
        ImpostoLei12345 lei12345 = new ImpostoLei12345();
        ImpostoFactory factory = new ImpostoFactory(List.of(lei12345), "Lei12345");

        IImpostoService ativo = factory.getAtivo();

        assertNotNull(ativo);
        assertEquals("Lei12345", ativo.getCodigo());
    }

    @Test
    void deveLancarExcecaoParaLeiInexistente() {
        ImpostoLei12345 lei12345 = new ImpostoLei12345();

        assertThrows(IllegalArgumentException.class,
                () -> new ImpostoFactory(List.of(lei12345), "LeiInexistente"));
    }

    @Test
    void deveListarEstrategiasDisponiveis() {
        ImpostoLei12345 lei12345 = new ImpostoLei12345();
        ImpostoFactory factory = new ImpostoFactory(List.of(lei12345), "Lei12345");

        List<String> disponiveis = factory.listarDisponiveis();

        assertTrue(disponiveis.contains("Lei12345"));
    }

    @Test
    void deveRetornarLeiAtiva() {
        ImpostoLei12345 lei12345 = new ImpostoLei12345();
        ImpostoFactory factory = new ImpostoFactory(List.of(lei12345), "Lei12345");

        assertEquals("Lei12345", factory.getLeiAtiva());
    }
}
