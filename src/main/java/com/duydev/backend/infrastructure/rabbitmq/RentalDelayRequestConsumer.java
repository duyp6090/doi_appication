package com.duydev.backend.infrastructure.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.duydev.backend.config.RabbitmqConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Rental-Delay-Request-Consumer")
@Service
@RequiredArgsConstructor
public class RentalDelayRequestConsumer {

    @RabbitListener(queues = RabbitmqConfig.DLX_QUEUE_NAME)
    public void receiveDelayRequest(String message){
        log.info("Received delay request rental: {}", message);
    }
}
