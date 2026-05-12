package com.example.pizzaria.Dominio.Servicos.Fakes;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.IEntregaService;

@Service
public class EntregaServiceFake implements IEntregaService {

    @Override
    public void agendarEntrega(Pedido pedido) {
        // Simula agendamento e atualiza status do pedido em memória
        pedido.setStatus(Pedido.Status.TRANSPORTE);
    }
}
