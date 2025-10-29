package com.carlosruanpucrs.tc2_monolitico.model.dto;

import com.carlosruanpucrs.tc2_monolitico.enums.SituacaoPagamentoInssEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PagamentoInssDto {

    String id;
    Integer numeroConta;
    Integer numeroBeneficio;
    BigDecimal valor;
    LocalDate dataPagamento;
    SituacaoPagamentoInssEnum situacao;
    String comprovantePagamento;
}
