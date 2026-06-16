package com.example.pizzaria.Dominio.Servicos;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.CardapioRepository;
import com.example.pizzaria.Dominio.Entidades.CabecalhoCardapio;
import com.example.pizzaria.Dominio.Entidades.Cardapio;
import com.example.pizzaria.Dominio.Entidades.Produto;
import com.example.pizzaria.Dominio.Exceptions.RecursoNaoEncontradoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardapioService {

    private final CardapioRepository cardapioRepository;
    private final JdbcTemplate jdbcTemplate;

    public Cardapio recuperaCardapio(long id) {
        Cardapio cardapio = cardapioRepository.recuperaPorId(id);
        if (cardapio == null) {
            throw new RecursoNaoEncontradoException("Cardápio ID " + id + " não encontrado.");
        }
        return cardapio;
    }

    public List<CabecalhoCardapio> recuperaListaDeCardapios() {
        return cardapioRepository.cardapiosDisponiveis();
    }

    public List<Produto> recuperaSugestoesDoChef() {
        return cardapioRepository.indicacoesDoChef();
    }

    public long getCardapioCorrenteId() {
        String valor = jdbcTemplate.queryForObject(
            "SELECT valor FROM configuracoes WHERE chave = 'cardapio_corrente_id'", String.class);
        return Long.parseLong(valor);
    }

    public void setCardapioCorrenteId(long id) {
        Cardapio cardapio = cardapioRepository.recuperaPorId(id);
        if (cardapio == null) {
            throw new RecursoNaoEncontradoException(
                    "Cardápio ID " + id + " não encontrado. Não é possível definir como corrente.");
        }
        jdbcTemplate.update("UPDATE configuracoes SET valor = ? WHERE chave = 'cardapio_corrente_id'",
            String.valueOf(id));
    }
}
