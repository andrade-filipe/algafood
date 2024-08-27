package com.esr.algafood.core.modelMapper;

import com.esr.algafood.application.model.dto.EnderecoDTO;
import com.esr.algafood.application.model.dto.RestauranteDTO;
import com.esr.algafood.domain.entity.Endereco;
import com.esr.algafood.domain.entity.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var enderecoToEnderecoDTOTypeMap = modelMapper
            .createTypeMap(Endereco.class, EnderecoDTO.class);

        enderecoToEnderecoDTOTypeMap.<String>addMapping(enderecoSrc ->
            enderecoSrc.getCidade().getEstado().getNome(), // propriedade que quero personalizar
            (dest, value) -> dest.getCidade().setEstado(value)); //o que eu quero que apareça no destino

        return modelMapper;
    }
}
