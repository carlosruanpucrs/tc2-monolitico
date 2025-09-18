package com.carlosruanpucrs.tc2_monolitico.exception;

public class ContaExisteException extends RuntimeException {

    private static final String MSG = "CONTA %S JA EXISTE";

    public ContaExisteException(Integer numeroConta) {
        super(String.format(MSG, numeroConta));
    }
}
