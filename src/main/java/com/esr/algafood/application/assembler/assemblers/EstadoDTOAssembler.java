package com.esr.algafood.application.assembler.assemblers;

import com.esr.algafood.application.model.dto.EstadoDTO;
import com.esr.algafood.domain.entity.Estado;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EstadoDTOAssembler {

    private ModelMapper modelMapper;

    public EstadoDTO toModel(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }

    public List<EstadoDTO> toCollectionModel(List<Estado> estados) {
        return estados.stream()
            .map(estado -> toModel(estado))
            .collect(Collectors.toList());
    }
}
