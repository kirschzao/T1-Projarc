package com.example.pizzaria.Adaptadores.Apresentacao.Presenters;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Cabeçalho de cardápio exposto na API")
public record CabecalhoCardapioPresenter(
        @Schema(description = "Identificador do cardápio", example = "1")
        long id,
        @Schema(description = "Título do cardápio", example = "Cardapio de Agosto")
        String titulo) {
}
