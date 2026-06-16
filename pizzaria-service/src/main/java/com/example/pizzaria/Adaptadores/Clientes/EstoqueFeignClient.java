package com.example.pizzaria.Adaptadores.Clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "estoque-service")
public interface EstoqueFeignClient {

    @PostMapping("/estoque/verificar")
    VerificacaoResponse verificarDisponibilidade(@RequestBody List<ItemVerificacaoDTO> itens);

    @PostMapping("/estoque/baixa")
    void darBaixa(@RequestBody List<ItemVerificacaoDTO> itens);

    record ItemVerificacaoDTO(Long produtoId, Long receitaId, int quantidade) {}

    record VerificacaoResponse(boolean disponivel, List<Long> produtosIndisponiveis) {}
}
