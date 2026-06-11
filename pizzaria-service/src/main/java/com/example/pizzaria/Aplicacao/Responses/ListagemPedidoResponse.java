package com.example.pizzaria.Aplicacao.Responses;

import java.time.LocalDateTime;

public record ListagemPedidoResponse(
    long pedidoId,
    String cpfCliente,
    String status,
    double valorCobrado,
    LocalDateTime dataCriacao
) {}
