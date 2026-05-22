package com.example.pizzaria.Dominio.Servicos;

public interface IPagamentoService {
    boolean processarPagamento(long pedidoId, double valor);
}
