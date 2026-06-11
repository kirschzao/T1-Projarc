package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import com.example.pizzaria.Aplicacao.Requests.SubmeterPedidoRequest;
import com.example.pizzaria.Aplicacao.Responses.CancelarPedidoResponse;
import com.example.pizzaria.Aplicacao.Responses.ListagemPedidoResponse;
import com.example.pizzaria.Aplicacao.Responses.PagarPedidoResponse;
import com.example.pizzaria.Aplicacao.Responses.PedidoResponse;
import com.example.pizzaria.Aplicacao.Responses.StatusPedidoResponse;
import com.example.pizzaria.Aplicacao.SubmeterPedidoUC;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final SubmeterPedidoUC submeterPedidoUC;
    private final ConsultarStatusPedidoUC consultarStatusPedidoUC;
    private final CancelarPedidoUC cancelarPedidoUC;
    private final PagarPedidoUC pagarPedidoUC;
    private final ListarPedidosEntreguesUC listarPedidosEntreguesUC;
    private final ListarPedidosClienteEntreguesUC listarPedidosClienteEntreguesUC;

    @PostMapping("/submeter")
    public PedidoResponse submeterPedido(
            @RequestHeader("X-User-Email") String emailAutenticado,
            @RequestBody SubmeterPedidoRequest request) {
        return submeterPedidoUC.run(emailAutenticado, request);
    }

    @GetMapping("/{id}")
    public StatusPedidoResponse consultarStatusPedido(
            @RequestHeader("X-User-Email") String emailAutenticado,
            @PathVariable long id) {
        return consultarStatusPedidoUC.run(emailAutenticado, id);
    }

    @PostMapping("/{id}/cancelar")
    public CancelarPedidoResponse cancelarPedido(
            @RequestHeader("X-User-Email") String emailAutenticado,
            @PathVariable long id) {
        return cancelarPedidoUC.run(emailAutenticado, id);
    }

    @PostMapping("/{id}/pagar")
    public PagarPedidoResponse pagarPedido(
            @RequestHeader("X-User-Email") String emailAutenticado,
            @PathVariable long id) {
        return pagarPedidoUC.run(emailAutenticado, id);
    }

    @GetMapping("/entregues")
    public List<ListagemPedidoResponse> listarPedidosEntregues(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return listarPedidosEntreguesUC.run(inicio, fim);
    }

    @GetMapping("/entregues/meus")
    public List<ListagemPedidoResponse> listarMeusPedidosEntregues(
            @RequestHeader("X-User-Email") String emailAutenticado,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return listarPedidosClienteEntreguesUC.run(emailAutenticado, inicio, fim);
    }
}
