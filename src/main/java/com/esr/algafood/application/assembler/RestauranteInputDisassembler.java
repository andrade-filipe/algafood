package com.esr.algafood.application.assembler;

import com.esr.algafood.application.model.input.RestauranteInput;
import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.entity.Restaurante;

public class RestauranteInputDisassembler {

    public static Restaurante toDomainObject(RestauranteInput restauranteInput){
        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        Restaurante restaurante = new Restaurante();

        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
