package com.carlosruanpucrs.tc2_monolitico.model.dto;

import com.carlosruanpucrs.tc2_monolitico.enums.OperacaoEnum;
import com.carlosruanpucrs.tc2_monolitico.enums.TipoMovimentacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoFinanceiraDto {

    private String comprovante;
    private TipoMovimentacaoEnum tipoMovimentacao;
    private OperacaoEnum operacao;
    private LocalDateTime dataHora;
    private BigDecimal valor;
    private Integer numeroContaOrigem;
    private Integer numeroContaDestino;
}
