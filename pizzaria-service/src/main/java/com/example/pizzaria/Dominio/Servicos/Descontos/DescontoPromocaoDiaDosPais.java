package com.example.pizzaria.Dominio.Servicos.Descontos;

import org.springframework.stereotype.Service;

@Service
public class DescontoPromocaoDiaDosPais implements IDescontoService {
    @Override
    public String getCodigo() {
        return "PromocaoDiaDosPais";
    }

    @Override
    public String getDescricao() {
        return "15% de desconto para pedidos acima de R$80 (Dia dos Pais)";
    }

    @Override
    public double calcularDesconto(String emailCliente, double valorTotal) {
        if (valorTotal > 80.0) {
            return valorTotal * 0.15;
        }
        return 0.0;
    }
}
