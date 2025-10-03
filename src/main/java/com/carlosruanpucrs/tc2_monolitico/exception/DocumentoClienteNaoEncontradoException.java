package com.carlosruanpucrs.tc2_monolitico.exception;

public class DocumentoClienteNaoEncontradoException extends RuntimeException {

    private static final String MESSAGE = "DOCUMENTO %S NAO ENCONTRADO";

    public DocumentoClienteNaoEncontradoException(String numeroDocumento) {
        super(String.format(MESSAGE, numeroDocumento));
    }
}
