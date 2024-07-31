package com.esr.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException (String mensagem){
        super(mensagem);
    }
}
