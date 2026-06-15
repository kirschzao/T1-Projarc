package com.example.pizzaria.Aplicacao.Responses;

import java.util.List;

import com.example.pizzaria.Dominio.Entidades.CabecalhoCardapio;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta contendo a lista de cabeçalhos dos cardápios disponíveis")
public record CabecalhoCardapioResponse(
        @Schema(description = "Lista de cabeçalhos de cardápios disponíveis")
        List<CabecalhoCardapio> cabecalhos) {
}
