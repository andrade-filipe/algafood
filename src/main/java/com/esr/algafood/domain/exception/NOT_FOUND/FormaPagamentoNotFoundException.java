package com.esr.algafood.domain.exception.NOT_FOUND;

public class FormaPagamentoNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public FormaPagamentoNotFoundException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNotFoundException(Long formaPagamentoId) {
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", formaPagamentoId));
    }
}
