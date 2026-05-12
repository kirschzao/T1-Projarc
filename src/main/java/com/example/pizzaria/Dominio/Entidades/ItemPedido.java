package com.example.pizzaria.Dominio.Entidades;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemPedido {
    @NotNull
    private Produto item;

    @Positive
    private int quantidade;

}
