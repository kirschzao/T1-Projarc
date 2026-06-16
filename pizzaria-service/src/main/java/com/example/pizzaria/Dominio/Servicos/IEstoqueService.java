package com.example.pizzaria.Dominio.Servicos;

import java.util.List;

import com.example.pizzaria.Dominio.Entidades.ItemPedido;

public interface IEstoqueService {
    boolean verificaDisponibilidade(List<ItemPedido> itens);

    List<Long> identificarProdutosIndisponiveis(List<ItemPedido> itensDoPedido);

    void darBaixa(List<ItemPedido> itens);
}
