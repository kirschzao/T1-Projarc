package com.example.pizzaria.Adaptadores.Apresentacao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaria.Adaptadores.Apresentacao.Presenters.CabecalhoCardapioPresenter;
import com.example.pizzaria.Adaptadores.Apresentacao.Presenters.CardapioPresenter;
import com.example.pizzaria.Adaptadores.Apresentacao.Presenters.ItemCardapioPresenter;
import com.example.pizzaria.Aplicacao.CarregarCardapioCorrenteUC;
import com.example.pizzaria.Aplicacao.RecuperaListaCardapiosUC;
import com.example.pizzaria.Aplicacao.RecuperarCardapioUC;
import com.example.pizzaria.Aplicacao.Responses.CardapioResponse;
import com.example.pizzaria.Dominio.Entidades.Produto;
import com.example.pizzaria.Dominio.Servicos.CardapioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cardapio")
@Tag(name = "Cardápio", description = "Operações para consulta de cardápios e sugestões do chef")
public class CardapioController {
    private final RecuperarCardapioUC recuperaCardapioUC;
    private final RecuperaListaCardapiosUC recuperaListaCardapioUC;
    private final CarregarCardapioCorrenteUC carregarCardapioCorrenteUC;
    private final CardapioService cardapioService;


    @GetMapping("/corrente")
    @CrossOrigin("*")
    @Operation(
        summary = "Carregar cardápio corrente (UC3)", 
        description = "Retorna o cardápio padrão do sistema (ID 1 nesta versão) para que o cliente possa iniciar a montagem do pedido."
    )
    public CardapioPresenter recuperaCardapioCorrente() {
        CardapioResponse cardapioResponse = carregarCardapioCorrenteUC.run();
        return mapearParaPresenter(cardapioResponse);
    }

    @GetMapping("/{id}")
    @CrossOrigin("*")
    @Operation(summary = "Recuperar cardápio por ID", description = "Retorna os detalhes de um cardápio específico e as sugestões do chef vigentes.")
    public CardapioPresenter recuperaCardapio(@PathVariable(value="id") long id) {
        CardapioResponse cardapioResponse = recuperaCardapioUC.run(id);
        return mapearParaPresenter(cardapioResponse);
    }

    @GetMapping("/lista")
    @CrossOrigin("*")
    @Operation(summary = "Listar cardápios disponíveis", description = "Retorna uma lista simplificada com os títulos e IDs de todos os cardápios cadastrados.")
    public List<CabecalhoCardapioPresenter> recuperaListaCardapios() {
         return recuperaListaCardapioUC.run().cabecalhos().stream()
            .map(cabCar -> new CabecalhoCardapioPresenter(cabCar.id(), cabCar.titulo()))
            .toList();
    }

    @PutMapping("/corrente/{id}")
    @CrossOrigin("*")
    @Operation(summary = "Definir cardápio corrente", description = "Define qual cardápio será o corrente do sistema, retornado pelo endpoint /cardapio/corrente.")
    public ResponseEntity<Map<String, Object>> definirCardapioCorrente(@PathVariable(value="id") long id) {
        cardapioService.setCardapioCorrenteId(id);
        return ResponseEntity.ok(Map.of("mensagem", "Cardápio corrente atualizado com sucesso.", "cardapioCorrenteId", id));
    }

    private CardapioPresenter mapearParaPresenter(CardapioResponse cardapioResponse) {
        Set<Long> conjIdSugestoes = new HashSet<>(cardapioResponse.getSugestoesDoChef().stream()
            .map(Produto::getId)
            .toList());

        List<ItemCardapioPresenter> itens = cardapioResponse.getCardapio().getProdutos().stream()
            .map(produto -> new ItemCardapioPresenter(
                produto.getId(),
                produto.getDescricao(),
                produto.getPreco(),
                conjIdSugestoes.contains(produto.getId()),
                produto.isDisponivel()
            ))
            .toList();

        return new CardapioPresenter(
            cardapioResponse.getCardapio().getCabecalhoCardapio().titulo(),
            itens
        );
    }
}