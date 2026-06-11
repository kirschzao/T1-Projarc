package com.example.pizzaria.Dominio.Servicos;

public interface IDescontoService {
    double calcularDesconto(String emailCliente, double valorTotal);
}
