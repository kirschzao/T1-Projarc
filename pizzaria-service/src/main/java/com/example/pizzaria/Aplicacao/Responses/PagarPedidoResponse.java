package com.example.pizzaria.Aplicacao.Responses;

public record PagarPedidoResponse(
        long pedidoId,
        boolean sucesso,
        String mensagem,
        String status) {
}
