package com.esr.algafood.application.assembler;

import com.esr.algafood.application.controller.RestauranteController;
import com.esr.algafood.application.model.dto.CozinhaDTO;
import com.esr.algafood.application.model.dto.RestauranteDTO;
import com.esr.algafood.domain.entity.Restaurante;

import java.util.List;
import java.util.stream.Collectors;

public class RestauranteModelAssembler {

    public static RestauranteDTO toModel(Restaurante restaurante) {
        CozinhaDTO cozinhaDTO = new CozinhaDTO();
        cozinhaDTO.setId(restaurante.getCozinha().getId());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());

        RestauranteDTO restauranteDTO = new RestauranteDTO();
        restauranteDTO.setId(restaurante.getId());
        restauranteDTO.setNome(restaurante.getNome());
        restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteDTO.setCozinha(cozinhaDTO);
        return restauranteDTO;
    }

    public static List<RestauranteDTO> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
            .map(RestauranteModelAssembler::toModel)
            .collect(Collectors.toList());
    }
}
