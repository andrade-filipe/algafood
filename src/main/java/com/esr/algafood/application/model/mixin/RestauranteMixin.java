package com.esr.algafood.application.model.mixin;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.entity.Endereco;
import com.esr.algafood.domain.entity.FormaPagamento;
import com.esr.algafood.domain.entity.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RestauranteMixin {

    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento;

    @JsonIgnore
    private List<Produto> produtos;
}
