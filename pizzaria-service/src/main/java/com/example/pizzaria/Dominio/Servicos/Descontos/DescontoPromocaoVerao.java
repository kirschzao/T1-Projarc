package com.example.pizzaria.Dominio.Servicos.Descontos;

import org.springframework.stereotype.Component;

@Component
public class DescontoPromocaoVerao implements IDescontoStrategy {
    @Override
    public String getCodigo() {
        return "PromocaoVerao";
    }

    @Override
    public String getDescricao() {
        return "10% de desconto para todos os pedidos (promocao de verao)";
    }

    @Override
    public double calcularDesconto(String emailCliente, double valorTotal) {
        return valorTotal * 0.10;
    }
}
