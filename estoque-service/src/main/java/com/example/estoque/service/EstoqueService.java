package com.example.estoque.service;

import com.example.estoque.dto.ItemVerificacaoRequest;
import com.example.estoque.dto.VerificacaoResponse;
import com.example.estoque.entity.IngredienteEntity;
import com.example.estoque.entity.ItemEstoqueEntity;
import com.example.estoque.entity.ReceitaEntity;
import com.example.estoque.repository.ItemEstoqueRepository;
import com.example.estoque.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final ItemEstoqueRepository itemEstoqueRepository;
    private final ReceitaRepository receitaRepository;

    public VerificacaoResponse verificarDisponibilidade(List<ItemVerificacaoRequest> itens) {
        // Calcula total de cada ingrediente necessário
        Map<Long, Integer> ingredientesNecessarios = calcularIngredientesNecessarios(itens);
        if (ingredientesNecessarios == null) {
            return new VerificacaoResponse(false, itens.stream().map(ItemVerificacaoRequest::produtoId).toList());
        }

        // Verifica disponibilidade de cada ingrediente
        boolean disponivel = true;
        for (Map.Entry<Long, Integer> entry : ingredientesNecessarios.entrySet()) {
            Optional<ItemEstoqueEntity> itemEstoque = itemEstoqueRepository.findByIngredienteId(entry.getKey());
            if (itemEstoque.isEmpty() || itemEstoque.get().getQuantidade() < entry.getValue()) {
                disponivel = false;
                break;
            }
        }

        if (disponivel) {
            return new VerificacaoResponse(true, List.of());
        }

        // Identifica quais produtos não podem ser atendidos
        List<Long> indisponiveis = identificarProdutosIndisponiveis(itens);
        return new VerificacaoResponse(false, indisponiveis);
    }

    private List<Long> identificarProdutosIndisponiveis(List<ItemVerificacaoRequest> itens) {
        List<Long> indisponiveis = new ArrayList<>();

        for (ItemVerificacaoRequest item : itens) {
            Optional<ReceitaEntity> receitaOpt = receitaRepository.findById(item.receitaId());
            if (receitaOpt.isEmpty()) {
                indisponiveis.add(item.produtoId());
                continue;
            }

            ReceitaEntity receita = receitaOpt.get();
            for (IngredienteEntity ingrediente : receita.getIngredientes()) {
                Optional<ItemEstoqueEntity> estoque = itemEstoqueRepository.findByIngredienteId(ingrediente.getId());
                if (estoque.isEmpty() || estoque.get().getQuantidade() < item.quantidade()) {
                    indisponiveis.add(item.produtoId());
                    break;
                }
            }
        }

        return indisponiveis;
    }

    @Transactional
    public void darBaixa(List<ItemVerificacaoRequest> itens) {
        Map<Long, Integer> ingredientesNecessarios = calcularIngredientesNecessarios(itens);
        if (ingredientesNecessarios == null) return;

        for (Map.Entry<Long, Integer> entry : ingredientesNecessarios.entrySet()) {
            ItemEstoqueEntity itemEstoque = itemEstoqueRepository.findByIngredienteId(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Ingrediente " + entry.getKey() + " não encontrado no estoque"));
            itemEstoque.setQuantidade(itemEstoque.getQuantidade() - entry.getValue());
            itemEstoqueRepository.save(itemEstoque);
        }
    }

    private Map<Long, Integer> calcularIngredientesNecessarios(List<ItemVerificacaoRequest> itens) {
        Map<Long, Integer> necessarios = new HashMap<>();

        for (ItemVerificacaoRequest item : itens) {
            Optional<ReceitaEntity> receitaOpt = receitaRepository.findById(item.receitaId());
            if (receitaOpt.isEmpty()) return null;

            ReceitaEntity receita = receitaOpt.get();
            for (IngredienteEntity ingrediente : receita.getIngredientes()) {
                necessarios.merge(ingrediente.getId(), item.quantidade(), Integer::sum);
            }
        }

        return necessarios;
    }
}
