package com.carlosruanpucrs.tc2_monolitico.message.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaNotificacaoBacenEvent {

    private String idTransacao;
    private Integer numeroConta;
    private String numeroDocumentoCliente;
    private LocalDate dataAberturaConta;
    private String nomeBanco;
}
