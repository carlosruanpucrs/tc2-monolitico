package com.carlosruanpucrs.tc2_monolitico.service;

import com.carlosruanpucrs.tc2_monolitico.api.request.ContratacaoContaRequest;
import com.carlosruanpucrs.tc2_monolitico.api.response.ContaResumoResponse;
import com.carlosruanpucrs.tc2_monolitico.enums.SituacaoContaEnum;
import com.carlosruanpucrs.tc2_monolitico.enums.TipoContaEnum;
import com.carlosruanpucrs.tc2_monolitico.exception.*;
import com.carlosruanpucrs.tc2_monolitico.mapper.ContaMapper;
import com.carlosruanpucrs.tc2_monolitico.model.entity.ContaEntity;
import com.carlosruanpucrs.tc2_monolitico.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaBacenService contaBacenService;

    public ContaResumoResponse contratarConta(ContratacaoContaRequest request) {
        var numeroConta = gerarNumeroConta();
        var numeroBeneficio = gerarNumeroBeneficio(request.getTipoConta());
        validarDocumentoClienteExistente(request.getNumeroDocumento());
        validarIdadeCliente(request.getDataNascimentoCliente());
        validarCEP(request.getCep());

        var conta = ContaMapper.mapToContaEntity(request, numeroConta, numeroBeneficio);
        var contaSalva = contaRepository.insert(conta);

        contaBacenService.enviarNotificacaoAberturaConta(contaSalva);

        return ContaMapper.mapToContaResumoResponse(contaSalva);
    }

    private Integer gerarNumeroConta() {
        return (int) (Math.random() * 29);
    }

    private Integer gerarNumeroBeneficio(TipoContaEnum tipoConta) {
        return Objects.equals(TipoContaEnum.CONTA_BENEFICIO, tipoConta) ? gerarNumeroConta() * 2 : null;
    }

    private void validarDocumentoClienteExistente(String documentoCliente) {
        var account = contaRepository.findContaEntityByDocumentoCliente(documentoCliente);
        if (Objects.nonNull(account)) {
            throw new DocumentoClienteExisteException(documentoCliente);
        }
    }

    private void validarIdadeCliente(LocalDate dataNascimento) {
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 18) {
            throw new MenorIdadeException();
        }
    }

    private void validarCEP(String cep) {
        if (Objects.isNull(cep) || !Pattern.matches("\\d{8}", cep)) {
            throw new CepInvalidoException(cep);
        }
    }

    public ContaEntity obtemContaPorNumero(Integer numeroConta) {
        return contaRepository.findContaEntityByNumeroConta(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException(numeroConta));
    }

    public ContaEntity obtemContaPorDocumento(String numeroDocumento) {
        return contaRepository.findContaEntityByDocumentoCliente(numeroDocumento)
                .orElseThrow(() -> new DocumentoClienteNaoEncontradoException(numeroDocumento));
    }

    public void atualizarSaldo(ContaEntity contaEntity) {
        contaRepository.save(contaEntity);
    }

    public void validarSaldoConta(BigDecimal valor, ContaEntity contaOrigem) {
        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException(contaOrigem.getNumeroConta());
        }
    }

    public void validarSituacaoContaBloqueada(ContaEntity conta) {
        if (Objects.equals(conta.getSituacao(), SituacaoContaEnum.BLOQUEADA)) {
            throw new ContaBloqueadaException(conta.getNumeroConta());
        }
    }
}
