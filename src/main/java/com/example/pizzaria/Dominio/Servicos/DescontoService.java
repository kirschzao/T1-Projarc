package com.example.pizzaria.Dominio.Servicos;

import org.springframework.stereotype.Service;

@Service
public class DescontoService {
    public double calcularDesconto(String emailCliente, double valorTotal) {
        boolean elegivelParaDesconto = false; 
        if (elegivelParaDesconto) {
            return valorTotal * 0.07;
        }
        return 0.0;
    }
}