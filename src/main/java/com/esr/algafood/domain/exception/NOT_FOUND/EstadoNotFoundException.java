package com.esr.algafood.domain.exception.NOT_FOUND;

public class EstadoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public EstadoNotFoundException(String mensagem){
        super(mensagem);
    }

    public EstadoNotFoundException(Long estadoId){
        this(String.format("Não existe um cadastro de estado com código %d", estadoId));
    }
}
