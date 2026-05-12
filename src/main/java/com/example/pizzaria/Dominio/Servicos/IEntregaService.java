package com.example.pizzaria.Dominio.Servicos;

import com.example.pizzaria.Dominio.Entidades.Pedido;

public interface IEntregaService {
    void agendarEntrega(Pedido pedido);
}
