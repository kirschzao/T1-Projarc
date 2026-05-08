package com.example.pizzaria.Aplicacao;

import org.springframework.stereotype.Component;
import com.example.pizzaria.Aplicacao.Responses.CardapioResponse;

@Component
public class CarregarCardapioCorrenteUC {
    private final RecuperarCardapioUC recuperarCardapioUC;
    
    private static final long CARDAPIO_CORRENTE_ID = 1L;

    public CarregarCardapioCorrenteUC(RecuperarCardapioUC recuperarCardapioUC) {
        this.recuperarCardapioUC = recuperarCardapioUC;
    }

    public CardapioResponse run() {
        return recuperarCardapioUC.run(CARDAPIO_CORRENTE_ID);
    }
}