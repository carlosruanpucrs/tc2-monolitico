package com.carlosruanpucrs.tc2_monolitico.service;

import com.carlosruanpucrs.tc2_monolitico.api.request.TransferenciaRequest;
import com.carlosruanpucrs.tc2_monolitico.api.response.TransferenciaResponse;
import com.carlosruanpucrs.tc2_monolitico.enums.SituacaoContaEnum;
import com.carlosruanpucrs.tc2_monolitico.exception.ContaBloqueadaException;
import com.carlosruanpucrs.tc2_monolitico.exception.SaldoInsuficienteException;
import com.carlosruanpucrs.tc2_monolitico.mapper.TransacaoMapper;
import com.carlosruanpucrs.tc2_monolitico.model.entity.ContaEntity;
import com.carlosruanpucrs.tc2_monolitico.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.carlosruanpucrs.tc2_monolitico.enums.TipoMovimentacaoEnum.TRANSFERENCIA_INTERNA;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransacaoService {

    private final ContaService contaService;
    private final TransacaoRepository transacaoRepository;

    public TransferenciaResponse transferir(TransferenciaRequest request) {
        var contaOrigem = contaService.obtemContaPorNumero(request.getContaOrigem());
        var contaDestino = contaService.obtemContaPorNumero(request.getContaDestino());

        validarSaldoConta(request, contaOrigem);
        validarSituacaoContaBloqueada(contaOrigem);

        contaOrigem.debitar(request.getValor());
        contaDestino.creditar(request.getValor());

        contaService.atualizarSaldo(contaOrigem);
        contaService.atualizarSaldo(contaDestino);

        var transacao = TransacaoMapper.mapToTransacaoEntity(request.getContaOrigem(), request.getContaDestino(), request.getValor(), TRANSFERENCIA_INTERNA);
        transacaoRepository.save(transacao);

        return TransacaoMapper.mapToTransferenciaResponse(transacao.getComprovante());
    }

    private void validarSaldoConta(TransferenciaRequest request, ContaEntity contaOrigem) {
        if (contaOrigem.getSaldo().compareTo(request.getValor()) < 0) {
            throw new SaldoInsuficienteException(request.getContaOrigem());
        }
    }

    private void validarSituacaoContaBloqueada(ContaEntity conta) {
        if (Objects.equals(conta.getSituacao(), SituacaoContaEnum.BLOQUEADA)) {
            throw new ContaBloqueadaException(conta.getNumeroConta());
        }
    }
}
