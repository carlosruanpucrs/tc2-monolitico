package com.carlosruanpucrs.tc2_monolitico.mapper;

import com.carlosruanpucrs.tc2_monolitico.api.response.ExtratoResponse;
import com.carlosruanpucrs.tc2_monolitico.api.response.TransferenciaResponse;
import com.carlosruanpucrs.tc2_monolitico.enums.OperacaoEnum;
import com.carlosruanpucrs.tc2_monolitico.model.entity.TransacaoEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.carlosruanpucrs.tc2_monolitico.enums.OperacaoEnum.CREDITO;
import static com.carlosruanpucrs.tc2_monolitico.enums.OperacaoEnum.DEBITO;

public class ExtratoMapper {

    public static ExtratoResponse mapToExtratoResponse(Integer numeroConta, BigDecimal saldo, List<TransacaoEntity> transacoes) {
        return ExtratoResponse.builder()
                .numeroContaOrigem(numeroConta)
                .saldo(saldo)
                .transferencias(mapToTransferenciaResponseList(numeroConta, transacoes))
                .build();
    }

    private static List<TransferenciaResponse> mapToTransferenciaResponseList(Integer numeroConta, List<TransacaoEntity> transacoes) {
        return transacoes.stream()
                .map(t -> mapToTransferenciaResponse(numeroConta, t))
                .toList();
    }

    public static TransferenciaResponse mapToTransferenciaResponse(Integer numeroConta, TransacaoEntity transacao) {
        return TransferenciaResponse.builder()
                .comprovante(transacao.getComprovante())
                .valor(transacao.getValor())
                .dataHora(transacao.getDataHora())
                .numeroConta(transacao.getContaDestino())
                .operacao(obtemOperacao(numeroConta, transacao.getContaOrigem()))
                .tipoMovimentacao(transacao.getTipoMovimentacao())
                .build();
    }

    private static OperacaoEnum obtemOperacao(Integer numeroConta, Integer numeroContaOrigem) {
        return Objects.equals(numeroConta, numeroContaOrigem)
                ? CREDITO
                : DEBITO;
    }

}
