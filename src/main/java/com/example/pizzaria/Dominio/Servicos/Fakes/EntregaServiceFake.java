package com.example.pizzaria.Dominio.Servicos.Fakes;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.EntregaService;

@Service
public class EntregaServiceFake implements EntregaService {

    @Override
    public void agendarEntrega(Pedido pedido) {
        pedido.setStatus(Pedido.Status.TRANSPORTE);
    }
}
