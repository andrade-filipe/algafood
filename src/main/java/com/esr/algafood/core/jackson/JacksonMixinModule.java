package com.esr.algafood.core.jackson;

import com.esr.algafood.application.model.mixin.CidadeMixin;
import com.esr.algafood.application.model.mixin.CozinhaMixin;
import com.esr.algafood.application.model.mixin.RestauranteMixin;
import com.esr.algafood.domain.entity.Cidade;
import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.entity.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
