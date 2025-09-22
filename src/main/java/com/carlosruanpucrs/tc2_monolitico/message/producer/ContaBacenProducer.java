package com.carlosruanpucrs.tc2_monolitico.message.producer;

import com.carlosruanpucrs.tc2_monolitico.config.RabbitConfig;
import com.carlosruanpucrs.tc2_monolitico.message.event.ContaNotificacaoBacenEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContaBacenProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviar(ContaNotificacaoBacenEvent evento) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_BACEN,
                RabbitConfig.ROUTING_KEY_BACEN,
                evento
        );
    }
}
