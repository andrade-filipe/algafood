package com.esr.algafood.domain.exception.NOT_FOUND;

public class CozinhaNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CozinhaNotFoundException(String mensagem){
        super(mensagem);
    }

    public CozinhaNotFoundException(Long cozinhaId){
        this(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
    }
}
