package com.example.pizzaria.Dominio.Dados;

import java.util.List;

import com.example.pizzaria.Dominio.Entidades.Produto;

public interface ProdutosRepository {
    Produto recuperaProdutoPorid(long id);
    List<Produto> recuperaProdutosCardapio(long id);
}
