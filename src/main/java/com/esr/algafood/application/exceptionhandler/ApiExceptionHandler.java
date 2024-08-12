package com.esr.algafood.application.exceptionhandler;

import com.esr.algafood.domain.exception.NOT_FOUND.EntityNotFoundException;
import com.esr.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException e){
        Problem problema = Problem.builder()
            .dahaHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .build();
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleBadRequestException(NegocioException e){
        Problem problema = Problem.builder()
            .dahaHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .build();
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(problema);
    }
}
