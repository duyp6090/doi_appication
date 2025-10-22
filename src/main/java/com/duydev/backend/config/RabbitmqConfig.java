package com.duydev.backend.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "RabbitmqConfig")
@Configuration
@EnableRabbit
public class RabbitmqConfig {

    public static final String DLX_EXCHANGE_NAME = "dlx.exchange.rental";
    public static final String DLX_QUEUE_NAME = "dlx.queue.rental";
    public static final String DLX_ROUTING_KEY = "dlx.routingkey.rental";

    public static final String MAIN_EXCHANGE_NAME = "main.exchange.rental";
    public static final String MAIN_QUEUE_NAME = "handle.rental.queue";
    public static final String MAIN_ROUTING_KEY = "main.routingkey.rental";

    // 1. Build exchange, queue, binding for handle dead request rental
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DLX_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue(DLX_QUEUE_NAME, true);
    }

    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(directExchange)
                .with(DLX_ROUTING_KEY);
    }

    // 2. Build exchange, queue, binding for handle main request rental
    @Bean
    public DirectExchange mainDirectExchange() {
        return new DirectExchange(MAIN_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue mainQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DLX_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        args.put("x-message-ttl", 60000); // 15 minutes
        return new Queue(MAIN_QUEUE_NAME, true, false, false, args);
    }

    @Bean
    public Binding mainBinding(DirectExchange mainDirectExchange, Queue mainQueue) {
        return BindingBuilder
                .bind(mainQueue)
                .to(mainDirectExchange)
                .with(MAIN_ROUTING_KEY);
    }
}
