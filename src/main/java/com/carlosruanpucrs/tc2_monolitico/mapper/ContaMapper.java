package com.carlosruanpucrs.tc2_monolitico.mapper;

import com.carlosruanpucrs.tc2_monolitico.api.request.ContratacaoContaRequest;
import com.carlosruanpucrs.tc2_monolitico.api.response.ContaResumoResponse;
import com.carlosruanpucrs.tc2_monolitico.message.event.ContaNotificacaoBacenEvent;
import com.carlosruanpucrs.tc2_monolitico.model.entity.ContaEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ContaMapper {

    public static ContaEntity mapToContaEntity(ContratacaoContaRequest request, Integer numeroConta) {
        return ContaEntity.builder()
                .documentoCliente(request.getNumeroDocumento())
                .nomeCliente(request.getNomeCliente())
                .numeroConta(numeroConta)
                .dataCriacao(LocalDate.now())
                .saldo(BigDecimal.ZERO)
                .situacao("ATIVADA")
                .build();
    }

    public static ContaResumoResponse mapToContaResumoResponse(ContaEntity contaEntity) {
        return ContaResumoResponse.builder()
                .documentoCliente(contaEntity.getDocumentoCliente())
                .nomeCliente(contaEntity.getNomeCliente())
                .numeroConta(contaEntity.getNumeroConta())
                .dataCriacao(contaEntity.getDataCriacao())
                .saldo(contaEntity.getSaldo())
                .situacao(contaEntity.getSituacao())
                .build();
    }

    public static ContaNotificacaoBacenEvent mapToContaNotificacaoBacenEvent(ContaEntity contaEntity) {
        return ContaNotificacaoBacenEvent.builder()
                .idTransacao(UUID.randomUUID().toString())
                .dataAberturaConta(contaEntity.getDataCriacao())
                .numeroDocumentoCliente(contaEntity.getDocumentoCliente())
                .numeroConta(contaEntity.getNumeroConta())
                .nomeBanco("BITBANK")
                .build();
    }
}
