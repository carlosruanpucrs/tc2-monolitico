package com.carlosruanpucrs.tc2_monolitico.api.response;

import com.carlosruanpucrs.tc2_monolitico.enums.OperacaoTransacaoEnum;
import com.carlosruanpucrs.tc2_monolitico.enums.TipoMovimentacaoEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferenciaResponse {

    String comprovante;
    Integer numeroConta;
    BigDecimal valor;
    LocalDateTime dataHora;
    OperacaoTransacaoEnum operacao;
    TipoMovimentacaoEnum tipoMovimentacao;
}
