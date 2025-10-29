package com.carlosruanpucrs.tc2_monolitico.mapper;

import com.carlosruanpucrs.tc2_monolitico.enums.SituacaoPagamentoInssEnum;
import com.carlosruanpucrs.tc2_monolitico.model.entity.PagamentoInssEntity;

public class InssMapper {

    public static PagamentoInssEntity mapToPagamentoInssEntity(PagamentoInssEntity entity, String comprovantePagamento, SituacaoPagamentoInssEnum situacaoPagamento) {
        entity.setComprovantePagamento(comprovantePagamento);
        entity.setSituacao(situacaoPagamento);
        return entity;
    }
}
