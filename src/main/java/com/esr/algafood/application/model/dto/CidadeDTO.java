package com.esr.algafood.application.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO {
    private Long id;
    private String nome;
    private EstadoDTO estado;
}
