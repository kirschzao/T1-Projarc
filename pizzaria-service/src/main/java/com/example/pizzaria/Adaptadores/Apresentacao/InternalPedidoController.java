package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.web.bind.annotation.*;

import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal/pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos (interno)", description = "Endpoints internos para atualizacao de status de pedidos pelo servico de entregas")
public class InternalPedidoController {

    private final PedidoService pedidoService;

    @PutMapping("/{id}/status")
    @Operation(summary = "Atualizar status do pedido",
               description = "Endpoint interno chamado pelo servico de entregas para atualizar o status do pedido (TRANSPORTE, ENTREGUE, etc).")
    public void atualizarStatus(@PathVariable long id, @RequestBody AtualizarStatusRequest request) {
        Pedido pedido = pedidoService.recuperarPorId(id);
        pedido.setStatus(Pedido.Status.valueOf(request.status()));
        pedidoService.atualizar(pedido);
    }

    public record AtualizarStatusRequest(String status) {}
}
