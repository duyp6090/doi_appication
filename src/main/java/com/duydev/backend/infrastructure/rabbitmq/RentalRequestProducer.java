package com.duydev.backend.infrastructure.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.duydev.backend.config.RabbitmqConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Rental-Request-Producer")
@Service
@RequiredArgsConstructor
public class RentalRequestProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendRequestRental(String message){
        log.info("Sending request is active for request rental: {}", message);
        rabbitTemplate.convertAndSend(
            RabbitmqConfig.MAIN_EXCHANGE_NAME,
            RabbitmqConfig.MAIN_ROUTING_KEY,
            message
        );
    }
}
