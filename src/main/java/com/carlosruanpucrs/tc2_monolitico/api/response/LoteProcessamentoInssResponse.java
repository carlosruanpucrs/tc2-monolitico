package com.carlosruanpucrs.tc2_monolitico.api.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoteProcessamentoInssResponse {

    Integer totalRegistros;
    Integer pagos;
    Integer pendentes ;
    Integer inconsistencias;
}
