package com.esr.algafood.domain.exception.NOT_FOUND;

import com.esr.algafood.domain.exception.NegocioException;
public abstract class EntityNotFoundException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException (String mensagem){
        super(mensagem);
    }
}
