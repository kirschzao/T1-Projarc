package com.example.pizzaria.Dominio.Servicos.Impostos;

public interface IImpostoStrategy {
    String getCodigo();
    double calcularImposto(double valorTotal);
}
