package com.esr.algafood.application.model.mixin;

import com.esr.algafood.domain.entity.Grupo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class UsuarioMixin {

    @JsonIgnore
    private OffsetDateTime dataCadastro;

    @JsonIgnore
    private List<Grupo> grupos;
}

