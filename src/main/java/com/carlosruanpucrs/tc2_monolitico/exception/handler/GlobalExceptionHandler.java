package com.carlosruanpucrs.tc2_monolitico.exception.handler;

import com.carlosruanpucrs.tc2_monolitico.exception.*;
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
    public ResponseEntity<SimpleError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
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

    @ExceptionHandler(CepInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SimpleError> handleCepInvalidoException(CepInvalidoException ex) {
        var simpleError = SimpleError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(simpleError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContaBloqueadaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<SimpleError> handleContaBloqueadaException(ContaBloqueadaException ex) {
        var simpleError = SimpleError.builder()
                .error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(simpleError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<SimpleError> handleContaNaoEncontradaException(ContaNaoEncontradaException ex) {
        var simpleError = SimpleError.builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(simpleError, HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(DocumentoClienteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<SimpleError> handleDocumentoClienteNaoEncontradoException(DocumentoClienteNaoEncontradoException ex) {
        var simpleError = SimpleError.builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(simpleError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MenorIdadeException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<SimpleError> handleMenorIdadeException(MenorIdadeException ex) {
        var simpleError = SimpleError.builder()
                .error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(simpleError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<SimpleError> handleSaldoInsuficienteException(SaldoInsuficienteException ex) {
        var simpleError = SimpleError.builder()
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(simpleError, HttpStatus.CONFLICT);
    }
}
