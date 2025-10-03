package com.carlosruanpucrs.tc2_monolitico.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum TipoMovimentacaoEnum {

    TRANSFERENCIA_INTERNA("TI"),
    BLOQUEIO_JUD("BJUD"),
    PAGAMENTO_BENEFICIO_INSS("INSS");

    final String descricao;
}
