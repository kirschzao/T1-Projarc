package com.example.estoque.service;

import com.example.estoque.dto.ItemVerificacaoRequest;
import com.example.estoque.dto.VerificacaoResponse;
import com.example.estoque.entity.IngredienteEntity;
import com.example.estoque.entity.ItemEstoqueEntity;
import com.example.estoque.entity.ReceitaEntity;
import com.example.estoque.repository.ItemEstoqueRepository;
import com.example.estoque.repository.ReceitaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstoqueServiceTest {

    @Mock private ItemEstoqueRepository itemEstoqueRepository;
    @Mock private ReceitaRepository receitaRepository;

    @InjectMocks
    private EstoqueService estoqueService;

    private IngredienteEntity ingrediente(Long id, String descricao) {
        IngredienteEntity e = new IngredienteEntity();
        e.setId(id);
        e.setDescricao(descricao);
        return e;
    }

    private ReceitaEntity receita(Long id, List<IngredienteEntity> ingredientes) {
        ReceitaEntity r = new ReceitaEntity();
        r.setId(id);
        r.setTitulo("Receita " + id);
        r.setIngredientes(ingredientes);
        return r;
    }

    private ItemEstoqueEntity itemEstoque(Long id, IngredienteEntity ingrediente, int quantidade) {
        ItemEstoqueEntity e = new ItemEstoqueEntity();
        e.setId(id);
        e.setIngrediente(ingrediente);
        e.setQuantidade(quantidade);
        return e;
    }

    @Test
    void deveRetornarDisponivelQuandoEstoqueSuficiente() {
        IngredienteEntity farinha = ingrediente(1L, "Farinha");
        ReceitaEntity receita = receita(1L, List.of(farinha));
        ItemEstoqueEntity estoqueFarinha = itemEstoque(1L, farinha, 10);

        when(receitaRepository.findById(1L)).thenReturn(Optional.of(receita));
        when(itemEstoqueRepository.findByIngredienteId(1L)).thenReturn(Optional.of(estoqueFarinha));

        List<ItemVerificacaoRequest> itens = List.of(new ItemVerificacaoRequest(1L, 1L, 2));
        VerificacaoResponse response = estoqueService.verificarDisponibilidade(itens);

        assertTrue(response.disponivel());
        assertTrue(response.produtosIndisponiveis().isEmpty());
    }

    @Test
    void deveRetornarIndisponivelQuandoEstoqueInsuficiente() {
        IngredienteEntity farinha = ingrediente(1L, "Farinha");
        ReceitaEntity receita = receita(1L, List.of(farinha));
        ItemEstoqueEntity estoqueFarinha = itemEstoque(1L, farinha, 1);

        when(receitaRepository.findById(1L)).thenReturn(Optional.of(receita));
        when(itemEstoqueRepository.findByIngredienteId(1L)).thenReturn(Optional.of(estoqueFarinha));

        List<ItemVerificacaoRequest> itens = List.of(new ItemVerificacaoRequest(1L, 1L, 5));
        VerificacaoResponse response = estoqueService.verificarDisponibilidade(itens);

        assertFalse(response.disponivel());
        assertTrue(response.produtosIndisponiveis().contains(1L));
    }

    @Test
    void deveRetornarIndisponivelQuandoReceitaNaoExiste() {
        when(receitaRepository.findById(99L)).thenReturn(Optional.empty());

        List<ItemVerificacaoRequest> itens = List.of(new ItemVerificacaoRequest(1L, 99L, 1));
        VerificacaoResponse response = estoqueService.verificarDisponibilidade(itens);

        assertFalse(response.disponivel());
    }

    @Test
    void deveDarBaixaNoEstoque() {
        IngredienteEntity farinha = ingrediente(1L, "Farinha");
        ReceitaEntity receita = receita(1L, List.of(farinha));
        ItemEstoqueEntity estoqueFarinha = itemEstoque(1L, farinha, 10);

        when(receitaRepository.findById(1L)).thenReturn(Optional.of(receita));
        when(itemEstoqueRepository.findByIngredienteId(1L)).thenReturn(Optional.of(estoqueFarinha));

        List<ItemVerificacaoRequest> itens = List.of(new ItemVerificacaoRequest(1L, 1L, 3));
        estoqueService.darBaixa(itens);

        assertEquals(7, estoqueFarinha.getQuantidade());
        verify(itemEstoqueRepository).save(estoqueFarinha);
    }

    @Test
    void deveRetornarIndisponivelQuandoIngredienteNaoTemEstoque() {
        IngredienteEntity farinha = ingrediente(1L, "Farinha");
        ReceitaEntity receita = receita(1L, List.of(farinha));

        when(receitaRepository.findById(1L)).thenReturn(Optional.of(receita));
        when(itemEstoqueRepository.findByIngredienteId(1L)).thenReturn(Optional.empty());

        List<ItemVerificacaoRequest> itens = List.of(new ItemVerificacaoRequest(1L, 1L, 1));
        VerificacaoResponse response = estoqueService.verificarDisponibilidade(itens);

        assertFalse(response.disponivel());
    }
}
