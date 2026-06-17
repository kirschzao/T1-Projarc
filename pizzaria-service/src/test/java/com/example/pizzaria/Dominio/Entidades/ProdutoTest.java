package com.example.pizzaria.Dominio.Entidades;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    private Receita receitaValida() {
        return new Receita(1L, "Massa Margherita", List.of(new Ingrediente(1L, "Farinha")));
    }

    @Test
    void deveCriarProdutoComDadosValidos() {
        Produto produto = new Produto(1L, "Pizza Margherita", receitaValida(), 5000);
        assertEquals(1L, produto.getId());
        assertEquals("Pizza Margherita", produto.getDescricao());
        assertEquals(5000, produto.getPreco());
        assertTrue(produto.isDisponivel());
    }

    @Test
    void deveCriarProdutoIndisponivel() {
        Produto produto = new Produto(1L, "Pizza", receitaValida(), 3000, false);
        assertFalse(produto.isDisponivel());
    }

    @Test
    void deveLancarExcecaoQuandoDescricaoNula() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto(1L, null, receitaValida(), 5000));
    }

    @Test
    void deveLancarExcecaoQuandoDescricaoVazia() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto(1L, "  ", receitaValida(), 5000));
    }

    @Test
    void deveLancarExcecaoQuandoReceitaNula() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto(1L, "Pizza", null, 5000));
    }

    @Test
    void deveLancarExcecaoQuandoPrecoInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto(1L, "Pizza", receitaValida(), 0));
        assertThrows(IllegalArgumentException.class,
                () -> new Produto(1L, "Pizza", receitaValida(), -100));
    }

    @Test
    void deveAtualizarPrecoValido() {
        Produto produto = new Produto(1L, "Pizza", receitaValida(), 5000);
        produto.setPreco(6000);
        assertEquals(6000, produto.getPreco());
    }

    @Test
    void deveLancarExcecaoAoSetarPrecoInvalido() {
        Produto produto = new Produto(1L, "Pizza", receitaValida(), 5000);
        assertThrows(IllegalArgumentException.class, () -> produto.setPreco(0));
    }

    @Test
    void deveAlterarDisponibilidade() {
        Produto produto = new Produto(1L, "Pizza", receitaValida(), 5000);
        assertTrue(produto.isDisponivel());
        produto.setDisponivel(false);
        assertFalse(produto.isDisponivel());
    }

    @Test
    void precoValidoDeveRetornarTrueParaPositivo() {
        assertTrue(Produto.precoValido(1));
        assertTrue(Produto.precoValido(10000));
    }

    @Test
    void precoValidoDeveRetornarFalseParaZeroOuNegativo() {
        assertFalse(Produto.precoValido(0));
        assertFalse(Produto.precoValido(-1));
    }
}
