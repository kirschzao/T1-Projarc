package com.example.pizzaria.Aplicacao;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.CancelarPedidoResponse;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.PedidoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CancelarPedidoUC {
    private final PedidoService pedidoService;

    public CancelarPedidoResponse run(long pedidoId) {
        String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

        Pedido pedido = pedidoService.recuperarPorId(pedidoId);
        pedidoService.validarPropriedade(pedido, emailAutenticado);
        pedidoService.cancelar(pedido);

        return new CancelarPedidoResponse(pedidoId, true, "Pedido cancelado com sucesso.", Pedido.Status.CANCELADO.name());
    }
}
