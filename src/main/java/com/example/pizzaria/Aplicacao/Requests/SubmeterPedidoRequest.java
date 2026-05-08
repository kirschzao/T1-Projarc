package com.example.pizzaria.Aplicacao.Requests;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para submeter um novo pedido")
public record SubmeterPedidoRequest(
    @Schema(description = "E-mail do usuário (simula o login)", example = "huguinho.pato@email.com")
    String emailCliente,
    
    @Schema(description = "Endereço de entrega preenchido no final do pedido", example = "Rua das Flores, 100")
    String enderecoEntrega,
    
    @Schema(description = "Lista de itens adicionados ao pedido")
    List<ItemPedidoRequest> itens
) {}
