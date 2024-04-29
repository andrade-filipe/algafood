package com.esr.algafood.domain.exception;

public class IsBeingUsedException extends RuntimeException{
    public IsBeingUsedException(String mensagem){
        super(mensagem);
    }
}
