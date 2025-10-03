package com.carlosruanpucrs.tc2_monolitico.message.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JudBloqueioEvent {

    String idBloqueio;
    String numeroDocumentoCliente;
    BigDecimal valorBloqueado;
    LocalDate dataEmissao;
}
