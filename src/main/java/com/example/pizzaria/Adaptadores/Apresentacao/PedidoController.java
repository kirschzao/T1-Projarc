package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaria.Aplicacao.SubmeterPedidoUC;
import com.example.pizzaria.Aplicacao.Requests.SubmeterPedidoRequest;
import com.example.pizzaria.Aplicacao.Responses.PedidoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
@Tag(name = "Pedidos", description = "Operações relacionadas à gestão de pedidos")
public class PedidoController {

    private final SubmeterPedidoUC submeterPedidoUC;

    public PedidoController(SubmeterPedidoUC submeterPedidoUC) {
        this.submeterPedidoUC = submeterPedidoUC;
    }

    @PostMapping("/submeter")
    @Operation(
        summary = "Submeter pedido para aprovação (UC4)", 
        description = "Verifica a disponibilidade de ingredientes no estoque, processa descontos/impostos e retorna o status da aprovação com os valores consolidados."
    )
    public PedidoResponse submeterPedido(@RequestBody SubmeterPedidoRequest request) {
        return submeterPedidoUC.run(request);
    }
}