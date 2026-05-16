package com.example.pizzaria.Dominio.Servicos.Fakes;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.EntregaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntregaServiceFake implements EntregaService {

    private final PedidoRepository pedidoRepository;

    @Override
    public void agendarEntrega(Pedido pedido) {
        pedido.setStatus(Pedido.Status.TRANSPORTE);
        pedidoRepository.atualizar(pedido);
    }
}
