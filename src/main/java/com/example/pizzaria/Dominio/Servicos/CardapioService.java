package com.example.pizzaria.Dominio.Servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.CardapioRepository;
import com.example.pizzaria.Dominio.Entidades.CabecalhoCardapio;
import com.example.pizzaria.Dominio.Entidades.Cardapio;
import com.example.pizzaria.Dominio.Entidades.Produto;

@Service
public class CardapioService implements ICardapioService {
    private final CardapioRepository cardapioRepository;

    public CardapioService(CardapioRepository cardapioRepository){
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public Cardapio recuperaCardapio(long Id){
        return cardapioRepository.recuperaPorId(Id);
    }

    @Override
    public List<CabecalhoCardapio> recuperaListaDeCardapios(){
        return cardapioRepository.cardapiosDisponiveis();
    }

    @Override
    public List<Produto> recuperaSugestoesDoChef(){
        return cardapioRepository.indicacoesDoChef();
    }
}
