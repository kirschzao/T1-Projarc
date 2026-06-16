package com.example.pizzaria.Dominio.Dados;

import com.example.pizzaria.Dominio.Entidades.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface PedidoRepository {
    Pedido salvar(Pedido pedido);
    Pedido recuperarPorId(long id);
    void atualizar(Pedido pedido);
    int contarPedidosPorClienteNoPeriodo(String cpf, int dias);
    List<Pedido> recuperarEntreguesPorData(LocalDateTime inicio, LocalDateTime fim);
    List<Pedido> recuperarEntreguesPorClienteEData(String cpf, LocalDateTime inicio, LocalDateTime fim);
    List<Map<String, Object>> recuperarHistoricoStatus(long pedidoId);
}
