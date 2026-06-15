package com.example.pizzaria.Adaptadores.Dados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Dominio.Dados.CardapioRepository;
import com.example.pizzaria.Dominio.Dados.ProdutosRepository;
import com.example.pizzaria.Dominio.Entidades.CabecalhoCardapio;
import com.example.pizzaria.Dominio.Entidades.Cardapio;
import com.example.pizzaria.Dominio.Entidades.Produto;

@Component
public class CardapioRepositoryJDBC implements CardapioRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProdutosRepository produtosRepository;

    @Autowired
    public CardapioRepositoryJDBC(JdbcTemplate jdbcTemplate, ProdutosRepository produtosRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.produtosRepository = produtosRepository;
    }

    @Override
    public Cardapio recuperaPorId(long id) {
        String sql = "SELECT id, titulo FROM cardapios WHERE id = ?";
        List<Cardapio> cardapios = this.jdbcTemplate.query(
                sql,
                ps -> ps.setLong(1, id),
                (rs, rowNum) -> new Cardapio(new CabecalhoCardapio(rs.getLong("id"), rs.getString("titulo")), null));
        if (cardapios.isEmpty()) {
            return null;
        }
        Cardapio cardapio = cardapios.getFirst();
        List<Produto> produtos = produtosRepository.recuperaProdutosCardapio(id);
        cardapio.setProdutos(produtos);
        return cardapio;
    }

    @Override
    public List<Produto> indicacoesDoChef() {
        return List.of(produtosRepository.recuperaProdutoPorid(2L));
    }

    @Override
    public List<CabecalhoCardapio> cardapiosDisponiveis() {
        String sql = "SELECT id, titulo FROM cardapios";
        List<CabecalhoCardapio> cabCardapios = this.jdbcTemplate.query(
                sql,
                ps -> {
                },
                (rs, rowNum) -> new CabecalhoCardapio(rs.getLong("id"), rs.getString("titulo")));
        return cabCardapios;
    }

}
