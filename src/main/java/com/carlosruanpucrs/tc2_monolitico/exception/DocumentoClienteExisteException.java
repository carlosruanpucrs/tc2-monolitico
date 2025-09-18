package com.carlosruanpucrs.tc2_monolitico.exception;

public class DocumentoClienteExisteException extends RuntimeException {

    private static final String MSG = "DOCUMENTO CLIENTE %S JA EXISTE";

    public DocumentoClienteExisteException(String documento) {
        super(String.format(MSG, documento));
    }
}
