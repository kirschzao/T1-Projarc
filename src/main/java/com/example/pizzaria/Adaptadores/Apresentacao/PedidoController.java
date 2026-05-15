package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaria.Aplicacao.CancelarPedidoUC;
import com.example.pizzaria.Aplicacao.ConsultarStatusPedidoUC;
import com.example.pizzaria.Aplicacao.Requests.CancelarPedidoRequest;
import com.example.pizzaria.Aplicacao.Requests.StatusPedidoRequest;
import com.example.pizzaria.Aplicacao.Requests.SubmeterPedidoRequest;
import com.example.pizzaria.Aplicacao.Responses.CancelarPedidoResponse;
import com.example.pizzaria.Aplicacao.Responses.PedidoResponse;
import com.example.pizzaria.Aplicacao.Responses.StatusPedidoResponse;
import com.example.pizzaria.Aplicacao.SubmeterPedidoUC;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Operações relacionadas à gestão de pedidos")
public class PedidoController {

    private final SubmeterPedidoUC submeterPedidoUC;
    private final ConsultarStatusPedidoUC consultarStatusPedidoUC;
    private final CancelarPedidoUC cancelarPedidoUC;

    @PostMapping("/submeter")
    @Operation(
        summary = "Submeter pedido para aprovação (UC4)", 
        description = "Verifica a disponibilidade de ingredientes no estoque, processa descontos/impostos e retorna o status da aprovação com os valores consolidados."
    )
    public PedidoResponse submeterPedido(@RequestBody SubmeterPedidoRequest request) {
        return submeterPedidoUC.run(request);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Consultar status do pedido (UC5)",
        description = "Permite ao cliente autenticado consultar o status atual de seu pedido pelo ID."
    )
    public StatusPedidoResponse consultarStatusPedido(
        @PathVariable long id,
        @RequestBody(required = false) StatusPedidoRequest request
    ) {
        long pedidoId = request != null ? request.pedidoId() : id;
        return consultarStatusPedidoUC.run(pedidoId);
    }

    @PostMapping("/{id}/cancelar")
    @Operation(
        summary = "Cancelar pedido (UC6)",
        description = "Permite ao cliente autenticado cancelar um pedido em estado APROVADO que ainda não foi pago."
    )
    public CancelarPedidoResponse cancelarPedido(
        @PathVariable long id,
        @RequestBody(required = false) CancelarPedidoRequest request
    ) {
        long pedidoId = request != null ? request.pedidoId() : id;
        return cancelarPedidoUC.run(pedidoId);
    }
}