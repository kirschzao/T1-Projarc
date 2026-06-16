package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.web.bind.annotation.*;

import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.PedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal/pedidos")
@RequiredArgsConstructor
public class InternalPedidoController {

    private final PedidoService pedidoService;

    @PutMapping("/{id}/status")
    public void atualizarStatus(@PathVariable long id, @RequestBody AtualizarStatusRequest request) {
        Pedido pedido = pedidoService.recuperarPorId(id);
        pedido.setStatus(Pedido.Status.valueOf(request.status()));
        pedidoService.atualizar(pedido);
    }

    public record AtualizarStatusRequest(String status) {}
}
