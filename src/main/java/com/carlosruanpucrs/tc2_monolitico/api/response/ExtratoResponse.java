package com.carlosruanpucrs.tc2_monolitico.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtratoResponse {

    Integer conta;
    BigDecimal saldo;
    List<TransferenciaResponse> transferencias;
}
