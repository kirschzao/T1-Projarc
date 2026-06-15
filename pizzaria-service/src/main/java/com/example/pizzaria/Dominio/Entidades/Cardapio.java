package com.example.pizzaria.Dominio.Entidades;

import java.util.List;

import lombok.Getter;

@Getter
public class Cardapio {
    private CabecalhoCardapio cabecalhoCardapio;
    private List<Produto> produtos;

    public Cardapio(CabecalhoCardapio cabecalhoCardapio, List<Produto> produtos) {
        this.cabecalhoCardapio = cabecalhoCardapio;
        this.produtos = produtos;
    }
    public void setProdutos(List<Produto> produtos){this.produtos = produtos;}
}
