package com.esr.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public EstadoNotFoundException(String mensagem){
        super(mensagem);
    }

    public EstadoNotFoundException(Long estadoId){
        this(String.format("Não existe um cadastro de estado com código %d", estadoId));
    }
}
