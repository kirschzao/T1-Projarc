package com.example.pizzaria.Aplicacao;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.StatusPedidoResponse;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.IPedidoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConsultarStatusPedidoUC {
    private final IPedidoService pedidoService;

    public StatusPedidoResponse run(long pedidoId) {
        // Obter email do usuário autenticado
        String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

        // Buscar o pedido
        Pedido pedido = pedidoService.recuperarPorId(pedidoId);

        // Validar se o pedido existe
        if (pedido == null) {
            return new StatusPedidoResponse(
                pedidoId,
                null,
                "Pedido não encontrado.",
                false
            );
        }

        // Validar se o pedido pertence ao usuário autenticado
        if (!pedido.getCliente().getEmail().equals(emailAutenticado)) {
            return new StatusPedidoResponse(
                pedidoId,
                null,
                "Acesso negado. Este pedido não pertence ao usuário autenticado.",
                false
            );
        }

        return new StatusPedidoResponse(
            pedidoId,
            pedido.getStatus().name(),
            "Status do pedido consultado com sucesso.",
            true
        );
    }
}
