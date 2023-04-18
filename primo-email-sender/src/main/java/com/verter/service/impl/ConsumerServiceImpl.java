package com.verter.service.impl;

import com.verter.dto.PurchaseDTO;
import com.verter.service.ConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    @Override
    public void receivedMessage(PurchaseDTO dto) {
        System.out.println(dto);
    }
}
