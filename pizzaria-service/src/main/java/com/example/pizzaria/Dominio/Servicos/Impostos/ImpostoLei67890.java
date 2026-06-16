package com.example.pizzaria.Dominio.Servicos.Impostos;

import org.springframework.stereotype.Service;

@Service
public class ImpostoLei67890 implements IImpostoService {
    @Override
    public String getCodigo() {
        return "Lei67890";
    }

    @Override
    public double calcularImposto(double valorTotal) {
        if (valorTotal <= 10000) {
            return valorTotal * 0.08;
        }
        return valorTotal * 0.12;
    }
}
