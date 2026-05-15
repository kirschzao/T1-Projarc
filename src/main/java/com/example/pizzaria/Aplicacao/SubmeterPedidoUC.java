package com.example.pizzaria.Aplicacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.pizzaria.Aplicacao.Requests.ItemPedidoRequest;
import com.example.pizzaria.Aplicacao.Requests.SubmeterPedidoRequest;
import com.example.pizzaria.Aplicacao.Responses.PedidoResponse;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Entidades.ItemPedido;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Exceptions.RegraDeNegocioException;
import com.example.pizzaria.Dominio.Servicos.ClienteService;
import com.example.pizzaria.Dominio.Servicos.PedidoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SubmeterPedidoUC {
    private final PedidoService pedidoService;
    private final ClienteService clienteService;

    public PedidoResponse run(SubmeterPedidoRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailAutenticado = authentication != null ? authentication.getName() : null;
        if (emailAutenticado == null || emailAutenticado.isBlank() || "anonymousUser".equals(emailAutenticado)) {
            throw new RegraDeNegocioException("Usuário não autenticado.");
        }

        Cliente cliente = clienteService.recuperarPorEmail(emailAutenticado);

        List<Long> produtoIds = new ArrayList<>();
        List<Integer> quantidades = new ArrayList<>();
        for (ItemPedidoRequest item : request.itens()) {
            produtoIds.add(item.produtoId());
            quantidades.add(item.quantidade());
        }

        List<ItemPedido> itens = pedidoService.validarECriarItens(produtoIds, quantidades);
        Pedido pedido = pedidoService.criarPedido(cliente, itens, request.enderecoEntrega());

        String erroEstoque = pedidoService.verificarEstoqueEAprovar(pedido, itens);
        if (erroEstoque != null) {
            return new PedidoResponse(pedido.getId(), "NEGADO", 0, 0, 0, 0, erroEstoque);
        }

        return new PedidoResponse(
            pedido.getId(),
            Pedido.Status.APROVADO.name(),
            pedido.getValor(),
            pedido.getImpostos(),
            pedido.getDesconto(),
            pedido.getValorCobrado(),
            "Pedido aprovado com sucesso. Aguardando pagamento."
        );
    }
}
