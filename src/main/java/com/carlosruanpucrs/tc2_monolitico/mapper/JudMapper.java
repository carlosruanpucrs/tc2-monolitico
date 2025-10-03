package com.carlosruanpucrs.tc2_monolitico.mapper;

import com.carlosruanpucrs.tc2_monolitico.message.event.JudBloqueioConfirmacaoEvent;
import com.carlosruanpucrs.tc2_monolitico.message.event.JudBloqueioEvent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class JudMapper {

    public static JudBloqueioConfirmacaoEvent map(JudBloqueioEvent evento, BigDecimal valorBloqueado, String status, String mensagem) {
        return JudBloqueioConfirmacaoEvent.builder()
                .idBloqueio(evento.getIdBloqueio())
                .numeroDocumentoCliente(evento.getNumeroDocumentoCliente())
                .dataExecucao(LocalDate.now())
                .status(status)
                .mensagem(mensagem)
                .valorBloqueado(Objects.equals("CONFIRMADA", status) ? evento.getValorBloqueado() : BigDecimal.ZERO)
                .build();
    }
}
