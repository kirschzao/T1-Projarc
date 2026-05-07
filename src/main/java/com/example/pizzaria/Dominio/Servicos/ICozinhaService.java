package com.example.pizzaria.Dominio.Servicos;

import com.example.pizzaria.Dominio.Entidades.Pedido;

public interface ICozinhaService {
    void chegadaDePedido(Pedido p);
    void pedidoPronto();
}