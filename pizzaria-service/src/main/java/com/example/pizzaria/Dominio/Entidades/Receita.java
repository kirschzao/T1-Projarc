package com.example.pizzaria.Dominio.Entidades;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Receita {
    @Positive
    private long id;

    @NotBlank
    private String titulo;

    @NotNull
    private List<Ingrediente> ingredientes;

}
