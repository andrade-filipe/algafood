package com.esr.algafood.application.assembler.assemblers;

import com.esr.algafood.application.model.dto.GrupoDTO;
import com.esr.algafood.domain.entity.Grupo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GrupoDTOAssembler {

    private ModelMapper modelMapper;

    public GrupoDTO toModel(Grupo grupo){
        return modelMapper.map(grupo, GrupoDTO.class);
    }

    public List<GrupoDTO> toCollectionModel(List<Grupo> grupos){
        return grupos.stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }
}
