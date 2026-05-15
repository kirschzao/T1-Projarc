package com.example.pizzaria.Aplicacao;

import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.CabecalhoCardapioResponse;
import com.example.pizzaria.Dominio.Servicos.CardapioService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecuperaListaCardapiosUC {
    private final CardapioService cardapioService;

    public CabecalhoCardapioResponse run() {
        return new CabecalhoCardapioResponse(cardapioService.recuperaListaDeCardapios());
    }
}
