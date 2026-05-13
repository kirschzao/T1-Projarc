package com.example.pizzaria.Aplicacao;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.CancelarPedidoResponse;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.IPedidoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CancelarPedidoUC {
    private final IPedidoService pedidoService;

    public CancelarPedidoResponse run(long pedidoId) {
        // Obter email do usuário autenticado
        String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

        // Buscar o pedido
        Pedido pedido = pedidoService.recuperarPorId(pedidoId);

        // Validar se o pedido existe
        if (pedido == null) {
            return new CancelarPedidoResponse(
                pedidoId,
                false,
                "Pedido não encontrado.",
                pedido != null ? pedido.getStatus().name() : null
            );
        }

        // Validar se o pedido pertence ao usuário autenticado
        if (!pedido.getCliente().getEmail().equals(emailAutenticado)) {
            return new CancelarPedidoResponse(
                pedidoId,
                false,
                "Acesso negado. Este pedido não pertence ao usuário autenticado.",
                pedido.getStatus().name()
            );
        }

        // Validar se o pedido está no estado APROVADO (único estado onde pode ser cancelado)
        if (pedido.getStatus() != Pedido.Status.APROVADO) {
            return new CancelarPedidoResponse(
                pedidoId,
                false,
                "Cancelamento negado. Pedido não está em estado APROVADO. Estado atual: " + 
                pedido.getStatus().name(),
                pedido.getStatus().name()
            );
        }

        // Realizar o cancelamento
        pedido.setStatus(Pedido.Status.CANCELADO);
        pedido = new Pedido(
            pedido.getId(),
            pedido.getCliente(),
            pedido.getItens(),
            Pedido.Status.CANCELADO,
            pedido.getValor(),
            pedido.getImpostos(),
            pedido.getDesconto(),
            pedido.getValorCobrado()
        );

        // Persistir o cancelamento
        pedidoService.atualizar(pedido);

        return new CancelarPedidoResponse(
            pedidoId,
            true,
            "Pedido cancelado com sucesso.",
            pedido.getStatus().name()
        );
    }
}
