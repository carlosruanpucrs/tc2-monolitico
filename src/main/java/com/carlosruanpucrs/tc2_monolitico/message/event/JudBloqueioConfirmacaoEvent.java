package com.carlosruanpucrs.tc2_monolitico.message.event;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JudBloqueioConfirmacaoEvent implements Serializable {

    String idBloqueio;
    String numeroDocumentoCliente;
    Integer numeroConta;
    BigDecimal valorBloqueado;
    LocalDate dataExecucao;
    String status;
    String mensagem;
}
