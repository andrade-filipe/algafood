package com.esr.algafood.core.modelMapper;

import com.esr.algafood.application.model.dto.RestauranteDTO;
import com.esr.algafood.domain.entity.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();

        // Para adicionar mapeamentos
//        modelMapper().createTypeMap(Restaurante.class, RestauranteDTO.class)
//            .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setTaxaFrete);
    }
}
