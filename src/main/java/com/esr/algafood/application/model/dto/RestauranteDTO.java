package com.esr.algafood.application.model.dto;

import com.esr.algafood.domain.entity.Cozinha;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteDTO {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;
}
