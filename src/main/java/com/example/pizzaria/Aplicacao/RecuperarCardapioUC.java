package com.example.pizzaria.Aplicacao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.CardapioResponse;
import com.example.pizzaria.Dominio.Entidades.Cardapio;
import com.example.pizzaria.Dominio.Entidades.Produto;
import com.example.pizzaria.Dominio.Servicos.CardapioService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecuperarCardapioUC {
    private final CardapioService cardapioService;

    public CardapioResponse run(long idCardapio) {
        Cardapio cardapio = cardapioService.recuperaCardapio(idCardapio);
        List<Produto> sugestoes = cardapioService.recuperaSugestoesDoChef();
        return new CardapioResponse(cardapio, sugestoes);
    }
}
