package com.esr.algafood.domain.exception.NOT_FOUND;

public class GrupoNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public GrupoNotFoundException(String mensagem) {
        super(mensagem);
    }

    public GrupoNotFoundException(Long estadoId) {
        this(String.format("Não existe um cadastro de grupo com código %d", estadoId));
    }
}
