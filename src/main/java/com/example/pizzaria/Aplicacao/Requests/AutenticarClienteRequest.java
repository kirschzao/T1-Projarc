package com.example.pizzaria.Aplicacao.Requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para autenticação do cliente")
public record AutenticarClienteRequest(
    @NotBlank
    @Email
    @Schema(description = "Email do cliente", example = "joao.silva@email.com")
    String email,

    @NotBlank
    @Schema(description = "Senha do cliente", example = "senha123")
    String senha
) {}
