package com.example.pizzaria.Aplicacao;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Responses.PagarPedidoResponse;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Exceptions.RegraDeNegocioException;
import com.example.pizzaria.Dominio.Servicos.ICozinhaService;
import com.example.pizzaria.Dominio.Servicos.IPagamentoService;
import com.example.pizzaria.Dominio.Servicos.PedidoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PagarPedidoUC {
    private final PedidoService pedidoService;
    private final IPagamentoService pagamentoService;
    private final ICozinhaService cozinhaService;

    public PagarPedidoResponse run(long pedidoId) {
        String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

        Pedido pedido = pedidoService.recuperarPorId(pedidoId);
        pedidoService.validarPropriedade(pedido, emailAutenticado);

        if (pedido.getStatus() != Pedido.Status.APROVADO) {
            throw new RegraDeNegocioException(
                    "Pagamento negado. Pedido não está em estado APROVADO. Estado atual: "
                            + pedido.getStatus().name());
        }

        boolean pagamentoRealizado = pagamentoService.processarPagamento(pedidoId, pedido.getValorCobrado());

        if (!pagamentoRealizado) {
            throw new RegraDeNegocioException("Pagamento recusado.");
        }

        pedido.setStatus(Pedido.Status.PAGO);
        pedidoService.atualizar(pedido);

        cozinhaService.chegadaDePedido(pedido);

        return new PagarPedidoResponse(pedidoId, true, "Pedido pago com sucesso e encaminhado para a cozinha.",
                pedido.getStatus().name());
    }
}
