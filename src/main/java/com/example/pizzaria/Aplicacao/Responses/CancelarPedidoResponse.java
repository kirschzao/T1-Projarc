package com.example.pizzaria.Aplicacao.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta do cancelamento de pedido")
public record CancelarPedidoResponse(
    @Schema(description = "ID do Pedido", example = "1")
    long pedidoId,
    @Schema(description = "Indica se o cancelamento foi bem-sucedido", example = "true")
    boolean sucesso,
    @Schema(description = "Mensagem descritiva", example = "Pedido cancelado com sucesso.")
    String mensagem,
    @Schema(description = "Status final do pedido", example = "CANCELADO")
    String statusPedido
) {}
