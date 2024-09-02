package com.esr.algafood.domain.exception.NOT_FOUND;

public class ProdutoNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public ProdutoNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNotFoundException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d",
            produtoId, restauranteId));
    }
}
