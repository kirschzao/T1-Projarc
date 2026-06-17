package com.example.estoque.controller;

import com.example.estoque.dto.ItemVerificacaoRequest;
import com.example.estoque.dto.VerificacaoResponse;
import com.example.estoque.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
@Tag(name = "Estoque", description = "Operacoes de verificacao e baixa de estoque de ingredientes")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping("/verificar")
    @Operation(summary = "Verificar disponibilidade de ingredientes",
               description = "Verifica se ha ingredientes suficientes no estoque para atender os itens do pedido.")
    public VerificacaoResponse verificarDisponibilidade(@RequestBody List<ItemVerificacaoRequest> itens) {
        return estoqueService.verificarDisponibilidade(itens);
    }

    @PostMapping("/baixa")
    @Operation(summary = "Dar baixa no estoque",
               description = "Reduz as quantidades de ingredientes no estoque conforme os itens consumidos.")
    public void darBaixa(@RequestBody List<ItemVerificacaoRequest> itens) {
        estoqueService.darBaixa(itens);
    }
}
