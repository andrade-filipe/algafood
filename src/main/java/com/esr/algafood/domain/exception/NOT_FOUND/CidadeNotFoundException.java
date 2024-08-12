package com.esr.algafood.domain.exception.NOT_FOUND;

public class CidadeNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CidadeNotFoundException(String mensagem){
        super(mensagem);
    }

    public CidadeNotFoundException(Long cidadeId){
        this(String.format("Não existe um cadastro de cidade com código %d", cidadeId));
    }
}
