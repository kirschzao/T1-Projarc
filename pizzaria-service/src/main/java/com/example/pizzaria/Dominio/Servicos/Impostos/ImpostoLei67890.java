package com.example.pizzaria.Dominio.Servicos.Impostos;

import org.springframework.stereotype.Component;

@Component
public class ImpostoLei67890 implements IImpostoStrategy {
    @Override
    public String getCodigo() {
        return "Lei67890";
    }

    @Override
    public double calcularImposto(double valorTotal) {
        if (valorTotal <= 100.0) {
            return valorTotal * 0.08;
        }
        return valorTotal * 0.12;
    }
}
