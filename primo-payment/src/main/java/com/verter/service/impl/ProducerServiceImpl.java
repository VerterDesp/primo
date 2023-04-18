package com.verter.service.impl;

import com.verter.dto.PurchaseDTO;
import com.verter.service.ProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public ProducerServiceImpl(RabbitTemplate rabbitTemplate,
                               @Value("${spring.rabbitmq.exchange}") String exchange,
                               @Value("${spring.rabbitmq.routingKey}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public void sendMessage(PurchaseDTO dto) {
        rabbitTemplate.convertAndSend(exchange,routingKey, dto);
    }
}
