package com.example.pizzaria.Aplicacao.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta da autenticação do cliente")
public record AutenticarClienteResponse(
    @Schema(description = "Indica se a autenticação foi bem-sucedida", example = "true")
    boolean sucesso,

    @Schema(description = "Mensagem descritiva", example = "Autenticação realizada com sucesso.")
    String mensagem,

    @Schema(description = "Nome do cliente autenticado", example = "João Silva")
    String nome,

    @Schema(description = "Email do cliente autenticado", example = "joao.silva@email.com")
    String email,

    @Schema(description = "Role do usuário", example = "CLIENTE")
    String role
) {}
