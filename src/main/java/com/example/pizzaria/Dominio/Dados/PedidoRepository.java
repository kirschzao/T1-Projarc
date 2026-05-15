package com.example.pizzaria.Dominio.Dados;

import com.example.pizzaria.Dominio.Entidades.Pedido;

public interface PedidoRepository {
    Pedido salvar(Pedido pedido);
    Pedido recuperarPorId(long id);
    void atualizar(Pedido pedido);
    int contarPedidosPorClienteNoPeriodo(String cpf, int dias);
}
