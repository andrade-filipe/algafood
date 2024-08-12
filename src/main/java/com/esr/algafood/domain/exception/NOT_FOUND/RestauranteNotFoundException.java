package com.esr.algafood.domain.exception.NOT_FOUND;

public class RestauranteNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public RestauranteNotFoundException(String mensagem){
        super(mensagem);
    }

    public RestauranteNotFoundException(Long restauranteId){
        this(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
    }
}
