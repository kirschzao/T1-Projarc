package com.example.pizzaria.Dominio.Entidades;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class Produto {
    private long id;

    @NotBlank
    private String descricao;

    @NotNull
    private Receita receita;

    @Positive
    private int preco;

    public Produto(long id, String descricao, Receita receita, int preco) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descricao nao pode ser nula ou vazia");
        }
        if (receita == null) {
            throw new IllegalArgumentException("Receita nao pode ser nula");
        }
        if (!Produto.precoValido(preco)) {
            throw new IllegalArgumentException("Preco invalido: " + preco);
        }

        this.id = id;
        this.descricao = descricao;
        this.receita = receita;
        this.preco = preco;
    }

    public void setPreco(int preco) {
        if (!Produto.precoValido(preco))
            throw new IllegalArgumentException("Preco invalido: " + preco);
        this.preco = preco;
    }

    // Valida um preco (preco em centavos)
    public static boolean precoValido(int preco) {
        return preco > 0;
    }   

    @Override
    public String toString() {
        return "Produto [id=" + id + ", descricao=" + descricao + ", receita=" + receita + ", preco=" + preco + "]";
    }

}
