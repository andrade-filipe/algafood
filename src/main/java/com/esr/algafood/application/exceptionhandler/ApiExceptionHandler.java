package com.esr.algafood.application.exceptionhandler;

import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.exception.NOT_FOUND.EntityNotFoundException;
import com.esr.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IsBeingUsedException.class)
    public ResponseEntity<?> handleIsBeingUsed(IsBeingUsedException e,
                                               WebRequest request){
        return handleExceptionInternal(
            e,
            e.getMessage(),
            new HttpHeaders(),
            HttpStatus.CONFLICT,
            request
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException e,
                                                     WebRequest request){
        return handleExceptionInternal(
            e,
            e.getMessage(),
            new HttpHeaders(),
            HttpStatus.NOT_FOUND,
            request
        );
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleBadRequestException(NegocioException e,
                                                       WebRequest request){
        return handleExceptionInternal(
            e,
            e.getMessage(),
            new HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        if(body == null){
            body = Problem.builder()
                .dahaHora(LocalDateTime.now())
                .mensagem(status.getReasonPhrase())
                .build();
        } else if(body instanceof String){
            body = Problem.builder()
                .dahaHora(LocalDateTime.now())
                .mensagem((String) body)
                .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
