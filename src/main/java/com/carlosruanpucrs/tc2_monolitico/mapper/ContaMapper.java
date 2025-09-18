package com.carlosruanpucrs.tc2_monolitico.mapper;

import com.carlosruanpucrs.tc2_monolitico.api.request.ContratacaoContaRequest;
import com.carlosruanpucrs.tc2_monolitico.api.response.ContaResumoResponse;
import com.carlosruanpucrs.tc2_monolitico.model.entity.ContaEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaMapper {

    public static ContaEntity map(ContratacaoContaRequest request, Integer numeroConta) {
        return ContaEntity.builder()
                .documentoCliente(request.getNumeroDocumento())
                .nomeCliente(request.getNomeCliente())
                .numeroConta(numeroConta)
                .dataCriacao(LocalDate.now())
                .saldo(BigDecimal.ZERO)
                .situacao("ATIVADA")
                .build();
    }

    public static ContaResumoResponse map(ContaEntity contaEntity) {
        return ContaResumoResponse.builder()
                .documentoCliente(contaEntity.getDocumentoCliente())
                .nomeCliente(contaEntity.getNomeCliente())
                .numeroConta(contaEntity.getNumeroConta())
                .dataCriacao(contaEntity.getDataCriacao())
                .saldo(contaEntity.getSaldo())
                .situacao(contaEntity.getSituacao())
                .build();
    }
}
