package com.example.pizzaria.Dominio.Servicos.Descontos;

public interface IDescontoService {
    String getCodigo();
    String getDescricao();
    double calcularDesconto(String emailCliente, double valorTotal);
}
