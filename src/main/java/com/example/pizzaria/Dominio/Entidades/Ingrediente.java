package com.example.pizzaria.Dominio.Entidades;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Ingrediente {
    @Positive
    private long id;

    @NotBlank
    private String descricao;

}
