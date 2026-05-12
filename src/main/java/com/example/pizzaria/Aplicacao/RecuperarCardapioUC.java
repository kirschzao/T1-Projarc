package com.example.pizzaria.Aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.CardapioResponse;
import com.example.pizzaria.Dominio.Entidades.Cardapio;
import com.example.pizzaria.Dominio.Entidades.Produto;
import com.example.pizzaria.Dominio.Servicos.ICardapioService;

@Component
public class RecuperarCardapioUC {
    private final ICardapioService cardapioService;

    @Autowired
    public RecuperarCardapioUC(ICardapioService cardapioService){
        this.cardapioService = cardapioService;
    }

    public CardapioResponse run(long idCardapio){
        Cardapio cardapio = cardapioService.recuperaCardapio(idCardapio);
        List<Produto> sugestoes = cardapioService.recuperaSugestoesDoChef();
        return new CardapioResponse(cardapio,sugestoes);
    }
}
