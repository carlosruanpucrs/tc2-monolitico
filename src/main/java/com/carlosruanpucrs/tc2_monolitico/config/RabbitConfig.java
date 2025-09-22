package com.carlosruanpucrs.tc2_monolitico.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_BACEN = "bacen.conta.exchange";
    public static final String QUEUE_BACEN = "bacen.conta.queue";
    public static final String ROUTING_KEY_BACEN = "conta";

    @Bean
    public DirectExchange bacenExchange() {
        return new DirectExchange(EXCHANGE_BACEN);
    }

    @Bean
    public Queue bacenQueue() {
        return new Queue(QUEUE_BACEN, true); // durable = true
    }

    @Bean
    public Binding bacenBinding(Queue bacenQueue, DirectExchange bacenExchange) {
        return BindingBuilder
                .bind(bacenQueue)
                .to(bacenExchange)
                .with(ROUTING_KEY_BACEN);
    }
}
