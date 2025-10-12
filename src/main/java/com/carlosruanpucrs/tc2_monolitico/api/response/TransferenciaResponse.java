package com.carlosruanpucrs.tc2_monolitico.api.response;

import com.carlosruanpucrs.tc2_monolitico.enums.OperacaoTransacaoEnum;
import com.carlosruanpucrs.tc2_monolitico.enums.TipoMovimentacaoEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferenciaResponse {

    String comprovante;
    Integer numeroConta;
    BigDecimal valor;
    LocalDateTime dataHora;
    OperacaoTransacaoEnum operacao;
    TipoMovimentacaoEnum tipoMovimentacao;
}
