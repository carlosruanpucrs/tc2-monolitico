package com.carlosruanpucrs.tc2_monolitico.api.response;

import com.carlosruanpucrs.tc2_monolitico.enums.SituacaoContaEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContaResumoResponse {

    String documentoCliente;
    String nomeCliente;
    Integer numeroConta;
    Integer numeroBeneficio;
    SituacaoContaEnum situacao;
    LocalDate dataCriacao;
    BigDecimal saldo;
}
