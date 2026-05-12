package com.example.pizzaria.Dominio.Servicos;

import com.example.pizzaria.Dominio.Entidades.Produto;

public interface IProdutoService {
    Produto recuperaProdutoPorId(long id);
}
