package com.example.pizzaria.Adaptadores.Apresentacao.Presenters;

import java.util.LinkedList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Cardápio com seus itens, exposto na API")
public class CardapioPresenter {

    @Schema(description = "Item de um cardápio (produto com indicação de sugestão do chef)")
    public class ItemCardapioPresenter {
        @Schema(description = "Identificador do produto", example = "1")
        private Long id;

        @Schema(description = "Descrição do produto", example = "Pizza calabresa")
        private String descricao;

        @Schema(description = "Preço do produto em centavos", example = "5500")
        private int preco;

        @Schema(description = "Indica se o produto é uma sugestão do chef", example = "true")
        private boolean indicacao;

        public ItemCardapioPresenter(Long id, String descricao, int preco, boolean indicacao) {
            this.id = id;
            this.descricao = descricao;
            this.preco = preco;
            this.indicacao = indicacao;
        }

        public Long getId() {
            return id;
        }

        public String getDescricao() {
            return descricao;
        }

        public int getPreco() {
            return preco;
        }

        public boolean isIndicacao() {
            return indicacao;
        }
    }

    @Schema(description = "Título do cardápio", example = "Cardapio de Agosto")
    private String titulo;

    @Schema(description = "Lista de itens do cardápio")
    private List<ItemCardapioPresenter> itens;

    public CardapioPresenter(String titulo) {
        this.titulo = titulo;
        itens = new LinkedList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void insereItem(long id, String titulo, int preco, boolean sugestao) {
        itens.add(new ItemCardapioPresenter(id, titulo, preco, sugestao));
    }

    public List<ItemCardapioPresenter> getItens() {
        return itens;
    }

    public void add(ItemCardapioPresenter item) {
        itens.add(item);
    }
}
