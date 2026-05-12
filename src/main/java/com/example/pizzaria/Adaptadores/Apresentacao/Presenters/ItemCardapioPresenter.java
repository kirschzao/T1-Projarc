package com.example.pizzaria.Adaptadores.Apresentacao.Presenters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "Item de um cardápio (produto com indicação de sugestão do chef)")
@Getter
@RequiredArgsConstructor
public class ItemCardapioPresenter {
    @Schema(description = "Identificador do produto", example = "1")
    private final Long id;

    @Schema(description = "Descrição do produto", example = "Pizza calabresa")
    private final String descricao;

    @Schema(description = "Preço do produto em centavos", example = "5500")
    private final int preco;

    @Schema(description = "Indica se o produto é uma sugestão do chef", example = "true")
    private final boolean indicacao;
}
