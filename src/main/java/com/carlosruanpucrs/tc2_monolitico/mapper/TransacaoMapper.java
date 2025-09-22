package com.carlosruanpucrs.tc2_monolitico.mapper;

import com.carlosruanpucrs.tc2_monolitico.api.response.ComprovanteResponse;
import com.carlosruanpucrs.tc2_monolitico.enums.TipoMovimentacaoEnum;
import com.carlosruanpucrs.tc2_monolitico.model.entity.TransacaoEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoMapper {

    public static TransacaoEntity mapToTransacaoEntity(Integer contaOrigem, Integer contaDestino, BigDecimal valor,
                                                       TipoMovimentacaoEnum tipoMovimentacao) {
        return TransacaoEntity.builder()
                .comprovante(tipoMovimentacao.getDescricao().concat(UUID.randomUUID().toString()))
                .valor(valor)
                .contaOrigem(contaOrigem)
                .contaDestino(contaDestino)
                .tipoMovimentacao(tipoMovimentacao)
                .dataHora(LocalDateTime.now())
                .build();
    }

    public static ComprovanteResponse mapToTransferenciaResponse(String comprovante) {
        return ComprovanteResponse.builder()
                .comprovante(comprovante)
                .build();
    }
}
