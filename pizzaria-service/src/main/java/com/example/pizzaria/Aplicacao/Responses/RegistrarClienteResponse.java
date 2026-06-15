package com.example.pizzaria.Aplicacao.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta do registro de cliente")
public record RegistrarClienteResponse(
    @Schema(description = "Indica se o registro foi bem-sucedido", example = "true")
    boolean sucesso,

    @Schema(description = "Mensagem descritiva", example = "Cliente registrado com sucesso.")
    String mensagem,

    @Schema(description = "Email do cliente registrado", example = "joao.silva@email.com")
    String email
) {}
