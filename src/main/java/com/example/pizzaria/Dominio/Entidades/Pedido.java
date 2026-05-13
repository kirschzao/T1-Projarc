package com.example.pizzaria.Dominio.Entidades;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pedido {
    public enum Status {
        NOVO,
        APROVADO,
        PAGO,
        AGUARDANDO,
        PREPARACAO,
        PRONTO,
        TRANSPORTE,
        ENTREGUE,
        CANCELADO
    }

    @PositiveOrZero
    private long id;

    @NotNull
    private Cliente cliente;

    @NotNull
    private List<ItemPedido> itens;

    @NotNull
    private Status status;

    @PositiveOrZero
    private double valor;

    @PositiveOrZero
    private double impostos;

    @PositiveOrZero
    private double desconto;

    @PositiveOrZero
    private double valorCobrado;

    
    public void setStatus(Status status){
        this.status = status;
    }
}
