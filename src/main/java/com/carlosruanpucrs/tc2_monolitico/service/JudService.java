package com.carlosruanpucrs.tc2_monolitico.service;

import com.carlosruanpucrs.tc2_monolitico.exception.ContaNaoEncontradaException;
import com.carlosruanpucrs.tc2_monolitico.mapper.JudMapper;
import com.carlosruanpucrs.tc2_monolitico.message.event.JudBloqueioConfirmacaoEvent;
import com.carlosruanpucrs.tc2_monolitico.message.event.JudBloqueioEvent;
import com.carlosruanpucrs.tc2_monolitico.model.entity.ContaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Service
public class JudService {

    private final ContaService contaService;

    public JudBloqueioConfirmacaoEvent processar(JudBloqueioEvent evento) {
        ContaEntity conta;
        try {
            conta = contaService.obtemContaPorNumero(evento.getNumeroConta());
        } catch (ContaNaoEncontradaException e) {
            return JudMapper.map(evento, BigDecimal.ZERO, "RECUSADA", e.getMessage());
        }

        BigDecimal saldo = conta.getSaldo();
        BigDecimal valorSolicitadoBloqueio = evento.getValorBloqueado();

        if (saldo.compareTo(valorSolicitadoBloqueio) > 0) {
            conta.bloquearSaldo(valorSolicitadoBloqueio);
            contaService.atualizarSaldo(conta);
            return JudMapper.map(evento, conta.getSaldoBloqueado(), "CONFIRMADA", "BLOQUEIO_TOTAL");
        } else if (saldo.compareTo(BigDecimal.ZERO) > 0) {
            conta.bloquearSaldo(conta.getSaldo());
            contaService.atualizarSaldo(conta);
            return JudMapper.map(evento, conta.getSaldoBloqueado(), "CONFIRMADA", "BLOQUEIO_PARCIAL");
        } else {
            return JudMapper.map(evento, conta.getSaldoBloqueado(), "RECUSADA", "SALDO_ZERADO");
        }
    }
}