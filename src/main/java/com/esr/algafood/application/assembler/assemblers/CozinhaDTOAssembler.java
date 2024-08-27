package com.esr.algafood.application.assembler.assemblers;

import com.esr.algafood.application.model.dto.CozinhaDTO;
import com.esr.algafood.domain.entity.Cozinha;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CozinhaDTOAssembler {

    private ModelMapper modelMapper;

    public CozinhaDTO toModel(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public List<CozinhaDTO> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
            .map(cozinha -> toModel(cozinha))
            .collect(Collectors.toList());
    }
}
