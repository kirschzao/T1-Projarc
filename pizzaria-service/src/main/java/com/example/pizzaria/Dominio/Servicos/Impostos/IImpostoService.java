package com.example.pizzaria.Dominio.Servicos.Impostos;

public interface IImpostoService {
    String getCodigo();
    double calcularImposto(double valorTotal);
}
