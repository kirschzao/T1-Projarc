package com.example.pizzaria.Aplicacao.Responses;

import java.util.List;

import com.example.pizzaria.Dominio.Entidades.Cardapio;
import com.example.pizzaria.Dominio.Entidades.Produto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta com o cardápio recuperado e as sugestões do chef")
public class CardapioResponse {
    @Schema(description = "Cardápio recuperado, com cabeçalho e lista de produtos")
    private Cardapio cardapio;

    @Schema(description = "Lista de produtos indicados pelo chef")
    private List<Produto> sugestoesDoChef;

    public CardapioResponse(Cardapio cardapio, List<Produto> sugestoesDoChef) {
        this.cardapio = cardapio;
        this.sugestoesDoChef = sugestoesDoChef;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public List<Produto> getSugestoesDoChef() {
        return sugestoesDoChef;
    }
}
