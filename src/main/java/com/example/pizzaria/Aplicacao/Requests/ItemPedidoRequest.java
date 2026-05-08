package com.example.pizzaria.Aplicacao.Requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Item individual do pedido")
public record ItemPedidoRequest(
    @Schema(description = "ID do Produto no Cardápio", example = "1")
    long produtoId,
    
    @Schema(description = "Quantidade desejada", example = "2")
    int quantidade
) {}
