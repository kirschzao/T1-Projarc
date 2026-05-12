package com.example.pizzaria.Dominio.Servicos;

import org.springframework.stereotype.Service;

@Service
public class ImpostoService implements IImpostoService {
    @Override
    public double calcularImposto(double valorTotal) {
        return valorTotal * 0.10;
    }
}