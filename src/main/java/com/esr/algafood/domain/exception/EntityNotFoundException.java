package com.esr.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException (String mensagem){
        super(mensagem);
    }
}
