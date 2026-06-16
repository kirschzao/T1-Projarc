package com.example.pizzaria.Adaptadores.Mensageria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntregaMessage {
    private long pedidoId;
    private String enderecoEntrega;
}
