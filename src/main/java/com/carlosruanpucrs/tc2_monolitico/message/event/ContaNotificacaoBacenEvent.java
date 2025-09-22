package com.carlosruanpucrs.tc2_monolitico.message.event;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContaNotificacaoBacenEvent {

    String idTransacao;
    Integer numeroConta;
    String numeroDocumentoCliente;
    LocalDate dataAberturaConta;
    String nomeBanco;
}
