package com.verter.service.impl;

import com.verter.dto.PurchaseDTO;
import com.verter.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    @Override
    public void receivedMessage(PurchaseDTO dto) {
        log.info("new message in email sender");
        System.out.println(dto);
    }
}
