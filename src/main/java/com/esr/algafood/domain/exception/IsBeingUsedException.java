package com.esr.algafood.domain.exception;

public class IsBeingUsedException extends NegocioException{
    public IsBeingUsedException(String mensagem){
        super(mensagem);
    }
}
