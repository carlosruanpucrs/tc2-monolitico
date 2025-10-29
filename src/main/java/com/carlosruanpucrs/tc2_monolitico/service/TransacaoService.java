package com.carlosruanpucrs.tc2_monolitico.service;

import com.carlosruanpucrs.tc2_monolitico.api.request.TransferenciaRequest;
import com.carlosruanpucrs.tc2_monolitico.api.response.ComprovanteResponse;
import com.carlosruanpucrs.tc2_monolitico.api.response.ExtratoResponse;
import com.carlosruanpucrs.tc2_monolitico.enums.SituacaoPagamentoInssEnum;
import com.carlosruanpucrs.tc2_monolitico.mapper.ExtratoMapper;
import com.carlosruanpucrs.tc2_monolitico.mapper.InssMapper;
import com.carlosruanpucrs.tc2_monolitico.mapper.TransacaoMapper;
import com.carlosruanpucrs.tc2_monolitico.model.entity.PagamentoInssEntity;
import com.carlosruanpucrs.tc2_monolitico.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.carlosruanpucrs.tc2_monolitico.enums.TipoMovimentacaoEnum.PAGAMENTO_BENEFICIO_INSS;
import static com.carlosruanpucrs.tc2_monolitico.enums.TipoMovimentacaoEnum.TRANSFERENCIA_INTERNA;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransacaoService {

    private final ContaService contaService;
    private final TransacaoRepository transacaoRepository;

    public ComprovanteResponse transferir(TransferenciaRequest request) {
        var contaOrigem = contaService.obtemContaPorNumero(request.getContaOrigem());
        var contaDestino = contaService.obtemContaPorNumero(request.getContaDestino());

        contaService.validarSaldoConta(request.getValor(), contaOrigem);
        contaService.validarSituacaoContaBloqueada(contaOrigem);

        contaOrigem.debitar(request.getValor());
        contaDestino.creditar(request.getValor());

        contaService.atualizarSaldo(contaOrigem);
        contaService.atualizarSaldo(contaDestino);

        var transacao = TransacaoMapper.mapToTransacaoEntity(request.getContaOrigem(), request.getContaDestino(), request.getValor(), TRANSFERENCIA_INTERNA);
        transacaoRepository.save(transacao);

        return TransacaoMapper.mapToTransferenciaResponse(transacao.getComprovante());
    }

    public ExtratoResponse gerarExtrato(Integer numeroConta) {
        var conta = contaService.obtemContaPorNumero(numeroConta);
        var transacoes = transacaoRepository.findByContaOrigemOrContaDestino(numeroConta, numeroConta);

        return ExtratoMapper.mapToExtratoResponse(numeroConta, conta.getSaldo(), transacoes);
    }

    public PagamentoInssEntity pagarInss(PagamentoInssEntity pagamentoInss) {
        try {
            var conta = contaService.obtemContaPorNumero(pagamentoInss.getNumeroConta());

            contaService.validarSituacaoContaBloqueada(conta);

            conta.creditar(pagamentoInss.getValorPagamento());
            contaService.atualizarSaldo(conta);

            var transacao = TransacaoMapper.mapToTransacaoEntity(conta.getNumeroConta(), null, pagamentoInss.getValorPagamento(), PAGAMENTO_BENEFICIO_INSS);
            transacaoRepository.save(transacao);
            return InssMapper.mapToPagamentoInssEntity(pagamentoInss, transacao.getComprovante(), SituacaoPagamentoInssEnum.PAGO);
        } catch (Exception e) {
            return InssMapper.mapToPagamentoInssEntity(pagamentoInss, null, SituacaoPagamentoInssEnum.INCONSISTIDO);
        }
    }
}
