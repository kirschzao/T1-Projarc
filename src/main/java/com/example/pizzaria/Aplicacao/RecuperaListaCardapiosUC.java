package com.example.pizzaria.Aplicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.CabecalhoCardapioResponse;
import com.example.pizzaria.Dominio.Servicos.ICardapioService;

@Component
public class RecuperaListaCardapiosUC {
    private final ICardapioService cardapioService;

    @Autowired
    public RecuperaListaCardapiosUC(ICardapioService cardapioService){
        this.cardapioService = cardapioService;
    }

    public CabecalhoCardapioResponse run(){
        return new CabecalhoCardapioResponse(cardapioService.recuperaListaDeCardapios());
    }    
}
