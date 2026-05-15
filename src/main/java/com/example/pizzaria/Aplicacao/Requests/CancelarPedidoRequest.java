package com.example.pizzaria.Aplicacao.Requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para cancelar um pedido")
public record CancelarPedidoRequest(
    @Schema(description = "ID do Pedido", example = "1")
    long pedidoId
) {}
