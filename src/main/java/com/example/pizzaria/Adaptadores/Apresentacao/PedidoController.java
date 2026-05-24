package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;

import com.example.pizzaria.Aplicacao.CancelarPedidoUC;
import com.example.pizzaria.Aplicacao.ConsultarStatusPedidoUC;
import com.example.pizzaria.Aplicacao.ListarPedidosClienteEntreguesUC;
import com.example.pizzaria.Aplicacao.ListarPedidosEntreguesUC;
import com.example.pizzaria.Aplicacao.PagarPedidoUC;
import com.example.pizzaria.Aplicacao.Requests.CancelarPedidoRequest;
import com.example.pizzaria.Aplicacao.Requests.StatusPedidoRequest;
import com.example.pizzaria.Aplicacao.Requests.SubmeterPedidoRequest;
import com.example.pizzaria.Aplicacao.Responses.CancelarPedidoResponse;
import com.example.pizzaria.Aplicacao.Responses.ListagemPedidoResponse;
import com.example.pizzaria.Aplicacao.Responses.PagarPedidoResponse;
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
    private final PagarPedidoUC pagarPedidoUC;
    private final ListarPedidosEntreguesUC listarPedidosEntreguesUC;
    private final ListarPedidosClienteEntreguesUC listarPedidosClienteEntreguesUC;

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

    @PostMapping("/{id}/pagar")
    @Operation(
        summary = "Pagar pedido (UC7)",
        description = "Permite ao cliente autenticado pagar um pedido em estado APROVADO. O pedido será encaminhado para a cozinha."
    )
    public PagarPedidoResponse pagarPedido(@PathVariable long id) {
        return pagarPedidoUC.run(id);
    }

    @GetMapping("/entregues")
    @Operation(
        summary = "Listar os pedidos entregues entre duas datas (UC8)",
        description = "Retorna todos os pedidos que estão no status ENTREGUE dentro do intervalo de datas especificado."
    )
    public List<ListagemPedidoResponse> listarPedidosEntregues(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return listarPedidosEntreguesUC.run(inicio, fim);
    }

    @GetMapping("/entregues/{id}")
    @Operation(
        summary = "Listar os pedidos de um determinado cliente entregues entre duas datas (UC9)",
        description = "Retorna os pedidos do cliente autenticado que estão no status ENTREGUE dentro do intervalo de datas especificado."
    )
    public List<ListagemPedidoResponse> listarMeusPedidosEntregues(
        @PathVariable String id,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return listarPedidosClienteEntreguesUC.run(id, inicio, fim);
    }
}