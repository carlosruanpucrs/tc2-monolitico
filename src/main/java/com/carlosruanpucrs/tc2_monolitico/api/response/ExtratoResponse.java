package com.carlosruanpucrs.tc2_monolitico.api.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExtratoResponse {

    Integer numeroContaOrigem;
    BigDecimal saldo;
    List<TransferenciaResponse> transferencias;
}
