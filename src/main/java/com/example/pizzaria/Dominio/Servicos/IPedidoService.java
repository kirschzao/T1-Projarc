package com.example.pizzaria.Dominio.Servicos;

import com.example.pizzaria.Dominio.Entidades.Pedido;

public interface IPedidoService {
    Pedido salvar(Pedido pedido);
    Pedido recuperarPorId(long id);
    void atualizar(Pedido pedido);
}
