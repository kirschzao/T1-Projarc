package com.example.pizzaria.Aplicacao.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta com o resultado da avaliação do pedido")
public record PedidoResponse(
    @Schema(description = "ID do Pedido gerado", example = "1")
    long pedidoId,
    @Schema(description = "Status do Pedido (APROVADO, NEGADO)", example = "APROVADO")
    String status,
    @Schema(description = "Valor total dos itens", example = "11000")
    double valorTotal,
    @Schema(description = "Impostos calculados", example = "1100")
    double impostos,
    @Schema(description = "Desconto aplicado", example = "0")
    double desconto,
    @Schema(description = "Valor final a ser cobrado", example = "12100")
    double valorCobrado,
    @Schema(description = "Mensagem detalhando o retorno", example = "Pedido aprovado e aguardando pagamento.")
    String mensagem
) {}