package com.example.pizzaria.Aplicacao.Requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para consultar o status de um pedido")
public record StatusPedidoRequest(
    @Schema(description = "ID do Pedido", example = "1")
    long pedidoId
) {}
