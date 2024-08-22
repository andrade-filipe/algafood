package com.esr.algafood.application.assembler.disassemblers;

import com.esr.algafood.application.model.dto.RestauranteDTO;
import com.esr.algafood.domain.entity.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDTO toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<RestauranteDTO> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
            .map(restaurante -> toModel(restaurante))
            .collect(Collectors.toList());
    }
}
