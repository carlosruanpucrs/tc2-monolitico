package com.carlosruanpucrs.tc2_monolitico.service;

import com.carlosruanpucrs.tc2_monolitico.mapper.ContaMapper;
import com.carlosruanpucrs.tc2_monolitico.message.producer.ContaBacenProducer;
import com.carlosruanpucrs.tc2_monolitico.model.entity.ContaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContaBacenService {

    private final ContaBacenProducer contaBacenProducer;

    public void enviarNotificacaoAberturaConta(ContaEntity conta){
        var eventoAux = ContaMapper.mapToContaNotificacaoBacenEvent(conta);
        contaBacenProducer.enviar(eventoAux);
    }
}
