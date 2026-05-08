package com.example.pizzaria.Dominio.Servicos;

import org.springframework.stereotype.Service;

@Service
public class ImpostoService {
    public double calcularImposto(double valorTotal) {
        return valorTotal * 0.10;
    }
}