package com.example.pizzaria.Dominio.Servicos;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.ProdutosRepository;
import com.example.pizzaria.Dominio.Entidades.Produto;

@Service
public class ProdutoService implements IProdutoService {

    private final ProdutosRepository produtosRepository;

    public ProdutoService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    @Override
    public Produto recuperaProdutoPorId(long id) {
        return produtosRepository.recuperaProdutoPorid(id);
    }

    @Override
    public void marcarComoIndisponivel(long id) {
        produtosRepository.atualizarDisponibilidade(id, false);
    }
}
