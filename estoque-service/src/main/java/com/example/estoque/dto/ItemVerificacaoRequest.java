package com.example.estoque.dto;

public record ItemVerificacaoRequest(Long produtoId, Long receitaId, int quantidade) {
}
