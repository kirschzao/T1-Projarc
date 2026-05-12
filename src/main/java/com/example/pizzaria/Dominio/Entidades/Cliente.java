package com.example.pizzaria.Dominio.Entidades;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cliente {
    @NotBlank
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    private String celular;

    @NotBlank
    private String endereco;

    @NotBlank
    private String email;

}
