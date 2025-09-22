package com.carlosruanpucrs.tc2_monolitico.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults
public class TransferenciaRequest {

    Integer contaOrigem;
    Integer contaDestino;
    BigDecimal valor;
}
