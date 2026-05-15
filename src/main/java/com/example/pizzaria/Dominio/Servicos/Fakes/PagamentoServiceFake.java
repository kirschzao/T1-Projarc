package com.example.pizzaria.Dominio.Servicos.Fakes;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Servicos.PagamentoService;

@Service
public class PagamentoServiceFake implements PagamentoService {

    @Override
    public boolean processarPagamento(long pedidoId, double valor) {
        return true;
    }
}
