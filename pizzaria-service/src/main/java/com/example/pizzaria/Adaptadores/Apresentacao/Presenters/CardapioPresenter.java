package com.example.pizzaria.Adaptadores.Apresentacao.Presenters;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "Cardápio com seus itens, exposto na API")
@Getter
@AllArgsConstructor
public class CardapioPresenter {

    @Schema(description = "Título do cardápio", example = "Cardapio de Agosto")
    @NotBlank
    private final String titulo;

    @Schema(description = "Lista de itens do cardápio")
    @NotNull
    private final List<ItemCardapioPresenter> itens;

}
