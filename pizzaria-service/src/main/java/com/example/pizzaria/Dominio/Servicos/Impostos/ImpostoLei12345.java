package com.example.pizzaria.Dominio.Servicos.Impostos;

import org.springframework.stereotype.Component;

@Component
public class ImpostoLei12345 implements IImpostoStrategy {
    @Override
    public String getCodigo() {
        return "Lei12345";
    }

    @Override
    public double calcularImposto(double valorTotal) {
        return valorTotal * 0.10;
    }
}
