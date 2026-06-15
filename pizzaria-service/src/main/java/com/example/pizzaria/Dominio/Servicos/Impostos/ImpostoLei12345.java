package com.example.pizzaria.Dominio.Servicos.Impostos;

import org.springframework.stereotype.Service;

@Service
public class ImpostoLei12345 implements IImpostoService {
    @Override
    public String getCodigo() {
        return "Lei12345";
    }

    @Override
    public double calcularImposto(double valorTotal) {
        return valorTotal * 0.10;
    }
}
