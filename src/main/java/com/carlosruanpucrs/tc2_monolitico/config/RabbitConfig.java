package com.carlosruanpucrs.tc2_monolitico.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    public static final String CONTAEXCHANGE_BACEN = "ccs.conta.abertura.exchange";
    public static final String CONTA_QUEUE_BACEN = "ccs.conta.abertura.queue";
    public static final String CONTA_ROUTING_KEY_BACEN = "abertura";

    public static final String CONTA_EXCHANGE_BACEN_DLX = "bacen.conta.exchange.dlx";
    public static final String CONTA_QUEUE_BACEN_DLQ = "bacen.conta.queue.dlq";
    public static final String CONTA_ROUTING_KEY_BACEN_DLQ = "bacen.conta.notificacao.dlq";

    public static final String JUD_BLOQUEIO_EXCHANGE = "jud.bloqueio.exchange";
    public static final String JUD_BLOQUEIO_QUEUE = "jud.bloqueio.queue";
    public static final String JUD_BLOQUEIO_RK = "jud.bloqueio.rk";

    public static final String JUD_BLOQUEIO_CONFIRMACAO_EXCHANGE = "jud.bloqueio.confirmacao.exchange";
    public static final String JUD_BLOQUEIO_CONFIRMACAO_QUEUE = "jud.bloqueio.confirmacao.queue";
    public static final String JUD_BLOQUEIO_CONFIRMACAO_RK = "jud.bloqueio.confirmacao.rk";

    @Bean
    public DirectExchange bacenExchange() {
        return new DirectExchange(CONTAEXCHANGE_BACEN);
    }

    @Bean
    public DirectExchange bacenDlxExchange() {
        return new DirectExchange(CONTA_EXCHANGE_BACEN_DLX);
    }

    @Bean
    public Queue bacenQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", CONTA_EXCHANGE_BACEN_DLX);
        args.put("x-dead-letter-routing-key", CONTA_ROUTING_KEY_BACEN_DLQ);
        return new Queue(CONTA_QUEUE_BACEN, true, false, false, args);
    }

    @Bean
    public Queue bacenDlqQueue() {
        return new Queue(CONTA_QUEUE_BACEN_DLQ, true);
    }

    @Bean
    public Binding bacenBinding(Queue bacenQueue, DirectExchange bacenExchange) {
        return BindingBuilder.bind(bacenQueue)
                .to(bacenExchange)
                .with(CONTA_ROUTING_KEY_BACEN);
    }

    @Bean
    public Binding bacenDlqBinding(Queue bacenDlqQueue, DirectExchange bacenDlxExchange) {
        return BindingBuilder.bind(bacenDlqQueue)
                .to(bacenDlxExchange)
                .with(CONTA_ROUTING_KEY_BACEN_DLQ);
    }

    @Bean
    public DirectExchange judBloqueioExchange() {
        return new DirectExchange(JUD_BLOQUEIO_EXCHANGE);
    }

    @Bean
    public Queue judBloqueioQueue() {
        return new Queue(JUD_BLOQUEIO_QUEUE);
    }

    @Bean
    public Binding judBloqueioBinding() {
        return BindingBuilder.bind(judBloqueioQueue())
                .to(judBloqueioExchange())
                .with(JUD_BLOQUEIO_RK);
    }

    @Bean
    public DirectExchange judBloqueioConfirmacaoExchange() {
        return new DirectExchange(JUD_BLOQUEIO_CONFIRMACAO_EXCHANGE);
    }

    @Bean
    public Queue judBloqueioConfirmacaoQueue() {
        return new Queue(JUD_BLOQUEIO_CONFIRMACAO_QUEUE);
    }

    @Bean
    public Binding judBloqueioConfirmacaoBinding() {
        return BindingBuilder.bind(judBloqueioConfirmacaoQueue())
                .to(judBloqueioConfirmacaoExchange())
                .with(JUD_BLOQUEIO_CONFIRMACAO_RK);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
