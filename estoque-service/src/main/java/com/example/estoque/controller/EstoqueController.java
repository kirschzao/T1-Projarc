package com.example.estoque.controller;

import com.example.estoque.dto.ItemVerificacaoRequest;
import com.example.estoque.dto.VerificacaoResponse;
import com.example.estoque.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping("/verificar")
    public VerificacaoResponse verificarDisponibilidade(@RequestBody List<ItemVerificacaoRequest> itens) {
        return estoqueService.verificarDisponibilidade(itens);
    }

    @PostMapping("/baixa")
    public void darBaixa(@RequestBody List<ItemVerificacaoRequest> itens) {
        estoqueService.darBaixa(itens);
    }
}
