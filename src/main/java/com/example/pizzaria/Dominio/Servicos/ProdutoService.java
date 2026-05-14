package com.example.pizzaria.Dominio.Servicos;

import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Dados.ProdutosRepository;
import com.example.pizzaria.Dominio.Entidades.Produto;
import com.example.pizzaria.Dominio.Exceptions.RecursoNaoEncontradoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutosRepository produtosRepository;

    public Produto recuperaProdutoPorId(long id) {
        Produto produto = produtosRepository.recuperaProdutoPorid(id);
        if (produto == null) {
            throw new RecursoNaoEncontradoException("Produto ID " + id + " não encontrado.");
        }
        return produto;
    }

    public void marcarComoIndisponivel(long id) {
        produtosRepository.atualizarDisponibilidade(id, false);
    }
}
