package com.esr.algafood.domain.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException (String mensagem){
        super(mensagem);
    }
}
