package com.carlosruanpucrs.tc2_monolitico.model.dto;

import com.carlosruanpucrs.tc2_monolitico.enums.OperacaoTransacaoEnum;
import com.carlosruanpucrs.tc2_monolitico.enums.TipoMovimentacaoEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovimentacaoFinanceiraDto {

    String comprovante;
    TipoMovimentacaoEnum tipoMovimentacao;
    OperacaoTransacaoEnum operacao;
    LocalDateTime dataHora;
    BigDecimal valor;
    Integer numeroContaOrigem;
    Integer numeroContaDestino;
}
