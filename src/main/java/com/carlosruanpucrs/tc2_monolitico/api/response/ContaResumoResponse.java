package com.carlosruanpucrs.tc2_monolitico.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaResumoResponse {

    private String documentoCliente;
    private String nomeCliente;
    private Integer numeroConta;
    private String situacao;
    private LocalDate dataCriacao;
    private BigDecimal saldo;
}
