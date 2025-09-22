package com.carlosruanpucrs.tc2_monolitico.exception;

public class ContaBloqueadaException extends RuntimeException {

    private static final String MESSAGE = "CONTA %s BLOQUEADA";

    public ContaBloqueadaException(Integer numeroConta) {
        super(String.format(MESSAGE, numeroConta));
    }
}
