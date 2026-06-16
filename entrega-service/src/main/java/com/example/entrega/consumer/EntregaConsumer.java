package com.example.entrega.consumer;

import com.example.entrega.config.RabbitMQConfig;
import com.example.entrega.dto.AtualizarStatusRequest;
import com.example.entrega.dto.EntregaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class EntregaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EntregaConsumer.class);
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final RestTemplate restTemplate;

    private static final String PIZZARIA_SERVICE_URL = "http://pizzaria-service";

    public EntregaConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ENTREGAS)
    public void receberEntrega(EntregaMessage message) {
        logger.info("Entrega recebida - Pedido #{} para {}", message.getPedidoId(), message.getEnderecoEntrega());

        // Atualiza status para TRANSPORTE
        atualizarStatus(message.getPedidoId(), "TRANSPORTE");

        // Simula tempo de entrega (5 segundos)
        scheduler.schedule(() -> {
            logger.info("Pedido #{} entregue com sucesso!", message.getPedidoId());
            atualizarStatus(message.getPedidoId(), "ENTREGUE");
        }, 5, TimeUnit.SECONDS);
    }

    private void atualizarStatus(long pedidoId, String status) {
        try {
            String url = PIZZARIA_SERVICE_URL + "/internal/pedidos/" + pedidoId + "/status";
            restTemplate.put(url, new AtualizarStatusRequest(status));
            logger.info("Status do pedido #{} atualizado para {}", pedidoId, status);
        } catch (Exception e) {
            logger.error("Erro ao atualizar status do pedido #{}: {}", pedidoId, e.getMessage());
        }
    }
}
