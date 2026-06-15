package com.example.pizzaria.Aplicacao.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta com o status atual do pedido")
public record StatusPedidoResponse(
    @Schema(description = "ID do Pedido", example = "1")
    long pedidoId,
    @Schema(description = "Status atual do Pedido", example = "APROVADO")
    String status,
    @Schema(description = "Mensagem descritiva", example = "Status do pedido consultado com sucesso.")
    String mensagem,
    @Schema(description = "Indica se a consulta foi bem-sucedida", example = "true")
    boolean sucesso
) {}
