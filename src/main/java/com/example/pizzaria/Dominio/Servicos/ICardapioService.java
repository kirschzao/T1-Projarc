package com.example.pizzaria.Dominio.Servicos;

import java.util.List;

import com.example.pizzaria.Dominio.Entidades.CabecalhoCardapio;
import com.example.pizzaria.Dominio.Entidades.Cardapio;
import com.example.pizzaria.Dominio.Entidades.Produto;

public interface ICardapioService {
    Cardapio recuperaCardapio(long id);
    List<CabecalhoCardapio> recuperaListaDeCardapios();
    List<Produto> recuperaSugestoesDoChef();
}
