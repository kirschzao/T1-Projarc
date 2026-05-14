package com.example.pizzaria.Dominio.Servicos;

public interface PagamentoService {
    boolean processarPagamento(long pedidoId, double valor);
}
