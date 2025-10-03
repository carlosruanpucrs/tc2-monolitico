package com.carlosruanpucrs.tc2_monolitico.message.consumer;

import com.carlosruanpucrs.tc2_monolitico.config.RabbitConfig;
import com.carlosruanpucrs.tc2_monolitico.message.event.JudBloqueioEvent;
import com.carlosruanpucrs.tc2_monolitico.service.JudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BloqueioJudConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final JudService judService;

    @RabbitListener(queues = RabbitConfig.JUD_BLOQUEIO_QUEUE)
    public void processarOrdemBloqueio(JudBloqueioEvent evento) {
        var confirmacao = judService.processar(evento);
        rabbitTemplate.convertAndSend(
                RabbitConfig.JUD_BLOQUEIO_CONFIRMACAO_EXCHANGE,
                RabbitConfig.JUD_BLOQUEIO_CONFIRMACAO_RK,
                confirmacao
        );
    }
}
