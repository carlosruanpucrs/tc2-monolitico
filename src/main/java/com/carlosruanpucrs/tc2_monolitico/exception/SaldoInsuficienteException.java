package com.carlosruanpucrs.tc2_monolitico.exception;

public class SaldoInsuficienteException extends RuntimeException {

    private static final String MESSAGE = "CONTA %s COM SALDO INSUFICIENTE";

    public SaldoInsuficienteException(Integer numeroConta) {
        super(String.format(MESSAGE, numeroConta));
    }
}
