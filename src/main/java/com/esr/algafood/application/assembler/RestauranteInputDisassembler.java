package com.esr.algafood.application.assembler;

import com.esr.algafood.application.model.input.RestauranteInput;
import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.entity.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput){
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante){
        restaurante.setCozinha(new Cozinha()); //Para que o JPA não entenda que é para trocar o ID da cozinha no banco
        modelMapper.map(restauranteInput, restaurante);
    }
}
