package com.example.pizzaria.Aplicacao;

import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.StatusPedidoResponse;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.PedidoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConsultarStatusPedidoUC {
    private final PedidoService pedidoService;

    public StatusPedidoResponse run(String emailAutenticado, long pedidoId) {
        Pedido pedido = pedidoService.recuperarPorId(pedidoId);
        pedidoService.validarPropriedade(pedido, emailAutenticado);

        var historico = pedidoService.recuperarHistoricoStatus(pedidoId);

        return new StatusPedidoResponse(pedidoId, pedido.getStatus().name(),
                "Status do pedido consultado com sucesso.", true, historico);
    }
}
