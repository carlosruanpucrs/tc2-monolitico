package com.carlosruanpucrs.tc2_monolitico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SimpleError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var simpleError = SimpleError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            simpleError.getFields().add(error.getField().concat(":").concat("Dado invalido"));
        });

        return new ResponseEntity<>(simpleError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DocumentoClienteExisteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<SimpleError> handleDocumentoClienteExisteException(DocumentoClienteExisteException ex) {
        var simpleError = SimpleError.builder()
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(simpleError, HttpStatus.CONFLICT);
    }
}
