package com.esr.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IsBeingUsedException extends NegocioException{
    public IsBeingUsedException(String mensagem){
        super(mensagem);
    }
}
