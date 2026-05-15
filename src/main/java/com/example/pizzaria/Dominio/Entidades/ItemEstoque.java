package com.example.pizzaria.Dominio.Entidades;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemEstoque {
    @NotNull
    private Ingrediente ingrediente;

    @PositiveOrZero
    private int quantidade;

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
