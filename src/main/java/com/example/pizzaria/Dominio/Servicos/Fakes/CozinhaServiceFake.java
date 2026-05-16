package com.example.pizzaria.Dominio.Servicos.Fakes;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.CozinhaService;
import com.example.pizzaria.Dominio.Servicos.EntregaService;

@Service
public class CozinhaServiceFake implements CozinhaService {
    private final Queue<Pedido> filaEntrada;
    private Pedido emPreparacao;
    private final Queue<Pedido> filaSaida;

    private final ScheduledExecutorService scheduler;

    private final PedidoRepository pedidoRepository;
    private final EntregaService entregaService;

    public CozinhaServiceFake(PedidoRepository pedidoRepository, EntregaService entregaService) {
        this.filaEntrada = new LinkedBlockingQueue<Pedido>();
        this.emPreparacao = null;
        this.filaSaida = new LinkedBlockingQueue<Pedido>();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.pedidoRepository = pedidoRepository;
        this.entregaService = entregaService;
    }

    private synchronized void colocaEmPreparacao(Pedido pedido) {
        if (pedido != null) {
            pedido.setStatus(Pedido.Status.PREPARACAO);
            pedidoRepository.atualizar(pedido);
            emPreparacao = pedido;
            scheduler.schedule(() -> pedidoPronto(), 5, TimeUnit.SECONDS);
        }
    }

    @Override
    public synchronized void chegadaDePedido(Pedido p) {
        filaEntrada.add(p);
        if (emPreparacao == null) {
            colocaEmPreparacao(filaEntrada.poll());
        }
    }

    @Override
    public synchronized void pedidoPronto() {
        if (emPreparacao != null) {
            emPreparacao.setStatus(Pedido.Status.PRONTO);
            pedidoRepository.atualizar(emPreparacao);
            filaSaida.add(emPreparacao);

            entregaService.agendarEntrega(emPreparacao);

            emPreparacao = null;
        }

        if (!filaEntrada.isEmpty()) {
            Pedido prox = filaEntrada.poll();
            scheduler.schedule(() -> colocaEmPreparacao(prox), 1, TimeUnit.SECONDS);
        }
    }
}
