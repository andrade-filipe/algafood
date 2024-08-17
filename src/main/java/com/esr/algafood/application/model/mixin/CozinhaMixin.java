package com.esr.algafood.application.model.mixin;

import com.esr.algafood.domain.entity.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class CozinhaMixin{
    @JsonIgnore
    private List<Restaurante> restaurantes;
}
