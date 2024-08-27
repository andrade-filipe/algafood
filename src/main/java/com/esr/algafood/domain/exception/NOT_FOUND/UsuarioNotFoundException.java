package com.esr.algafood.domain.exception.NOT_FOUND;

public class UsuarioNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public UsuarioNotFoundException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNotFoundException(Long usuarioId) {
        this(String.format("Não existe um cadastro de usuário com código %d", usuarioId));
    }
}
