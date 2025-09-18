package com.carlosruanpucrs.tc2_monolitico.exception;

public class MenorIdadeException extends RuntimeException {

    private static final String MSG = "Idade minima para abrir conta é 18 anos";

    public MenorIdadeException() {
        super(MSG);
    }
}
