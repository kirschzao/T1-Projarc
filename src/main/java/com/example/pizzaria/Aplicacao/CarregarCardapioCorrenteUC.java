package com.example.pizzaria.Aplicacao;

import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.CardapioResponse;
import com.example.pizzaria.Dominio.Servicos.CardapioService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarregarCardapioCorrenteUC {
    private final RecuperarCardapioUC recuperarCardapioUC;
    private final CardapioService cardapioService;

    public CardapioResponse run() {
        return recuperarCardapioUC.run(cardapioService.getCardapioCorrenteId());
    }
}
