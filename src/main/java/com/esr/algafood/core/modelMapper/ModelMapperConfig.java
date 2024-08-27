package com.esr.algafood.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        // Erro desconhecido, não consegui resolver
//        var enderecoToEnderecoDTOTypeMap = modelMapper
//            .createTypeMap(Endereco.class, EnderecoDTO.class);
//
//        enderecoToEnderecoDTOTypeMap.addMapping(enderecoSrc ->
//            enderecoSrc.getCidade().getEstado().getNome(), // propriedade que quero personalizar
//            (dest, value) -> dest.getCidade().setEstado((String)value)); //o que eu quero que apareça no destino

        return modelMapper;
    }
}
