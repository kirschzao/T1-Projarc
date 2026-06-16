package com.example.pizzaria.Adaptadores.Mensageria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Servicos.IEntregaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntregaServiceRabbitMQ implements IEntregaService {

    private static final Logger logger = LoggerFactory.getLogger(EntregaServiceRabbitMQ.class);
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void agendarEntrega(Pedido pedido) {
        EntregaMessage message = new EntregaMessage(
                pedido.getId(),
                pedido.getCliente().getEndereco());

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_ENTREGAS, message);
        logger.info("Pedido #{} enviado para fila de entregas", pedido.getId());
    }
}
