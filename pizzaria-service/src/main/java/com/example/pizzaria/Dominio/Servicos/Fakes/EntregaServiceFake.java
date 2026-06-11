package com.example.pizzaria.Dominio.Servicos.Fakes;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.IEntregaService;

@Service
public class EntregaServiceFake implements IEntregaService {

    private final PedidoRepository pedidoRepository;
    private final ScheduledExecutorService scheduler;

    public EntregaServiceFake(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void agendarEntrega(Pedido pedido) {
        pedido.setStatus(Pedido.Status.TRANSPORTE);
        pedidoRepository.atualizar(pedido);

        // Simula o tempo de entrega
        scheduler.schedule(() -> {
            pedido.setStatus(Pedido.Status.ENTREGUE);
            pedidoRepository.atualizar(pedido);
        }, 5, TimeUnit.SECONDS);
    }
}
