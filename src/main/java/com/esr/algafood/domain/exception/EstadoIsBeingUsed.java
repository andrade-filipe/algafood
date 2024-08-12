package com.esr.algafood.domain.exception;

public class EstadoIsBeingUsed extends IsBeingUsedException{
    public EstadoIsBeingUsed(String mensagem) {
        super(mensagem);
    }

    public EstadoIsBeingUsed(Long estadoId){
        this(String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
    }
}
