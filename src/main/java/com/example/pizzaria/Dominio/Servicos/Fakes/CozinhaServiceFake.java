package com.example.pizzaria.Dominio.Servicos.Fakes;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.CozinhaService;

@Service
public class CozinhaServiceFake implements CozinhaService {
    private final Queue<Pedido> filaEntrada;
    private Pedido emPreparacao;
    private final Queue<Pedido> filaSaida;

    private final ScheduledExecutorService scheduler;

    public CozinhaServiceFake() {
        filaEntrada = new LinkedBlockingQueue<Pedido>();
        emPreparacao = null;
        filaSaida = new LinkedBlockingQueue<Pedido>();
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    private synchronized void colocaEmPreparacao(Pedido pedido) {
        pedido.setStatus(Pedido.Status.PREPARACAO);
        emPreparacao = pedido;
        scheduler.schedule(() -> pedidoPronto(), 5, TimeUnit.SECONDS);
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
        emPreparacao.setStatus(Pedido.Status.PRONTO);
        filaSaida.add(emPreparacao);
        emPreparacao = null;
        if (!filaEntrada.isEmpty()) {
            Pedido prox = filaEntrada.poll();
            scheduler.schedule(() -> colocaEmPreparacao(prox), 1, TimeUnit.SECONDS);
        }
    }
}
