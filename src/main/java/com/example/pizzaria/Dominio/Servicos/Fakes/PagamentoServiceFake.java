package com.example.pizzaria.Dominio.Servicos.Fakes;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Servicos.IPagamentoService;

@Service
public class PagamentoServiceFake implements IPagamentoService {

    @Override
    public boolean processarPagamento(long pedidoId, double valor) {
        // sempre aprova pagamentos
        return true;
    }
}
