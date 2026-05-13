package com.example.pizzaria.Dominio.Servicos;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Entidades.Pedido;

@Service
public class PedidoService implements IPedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.salvar(pedido);
    }

    @Override
    public Pedido recuperarPorId(long id) {
        return pedidoRepository.recuperarPorId(id);
    }

    @Override
    public void atualizar(Pedido pedido) {
        pedidoRepository.atualizar(pedido);
    }
}
