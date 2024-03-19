package com.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    // Maneja excepciones de tipo IllegalArgumentException e IllegalStateException
    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex) {
        String bodyOfResponse = "This should be application specific";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyOfResponse);
    }

    // Maneja excepciones de tipo MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        // Retorna un mensaje indicando el parámetro y su valor inválido
        String bodyOfResponse = String.format("El parámetro '%s' tiene un valor inválido: '%s'", ex.getName(), ex.getValue());
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    // Maneja excepciones de tipo MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        // Retorna un mensaje indicando los campos y mensajes de error de la validación
        String bodyOfResponse = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse(ex.getMessage());
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    // Maneja todas las demás excepciones no capturadas por los manejadores anteriores
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        // Retorna un mensaje de error genérico
        String bodyOfResponse = "Error inesperado ocurrido: " + ex.getLocalizedMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

