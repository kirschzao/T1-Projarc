package com.example.entrega.consumer;

import com.example.entrega.dto.EntregaMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntregaConsumerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EntregaConsumer entregaConsumer;

    @Test
    void deveProcessarMensagemDeEntrega() {
        EntregaMessage message = new EntregaMessage(1L, "Rua A, 123");

        entregaConsumer.receberEntrega(message);

        verify(restTemplate).put(
                eq("http://pizzaria-service/internal/pedidos/1/status"),
                any());
    }

    @Test
    void deveAtualizarStatusParaTransporteAoReceber() {
        EntregaMessage message = new EntregaMessage(42L, "Rua B, 456");

        entregaConsumer.receberEntrega(message);

        verify(restTemplate).put(
                contains("/internal/pedidos/42/status"),
                any());
    }
}
