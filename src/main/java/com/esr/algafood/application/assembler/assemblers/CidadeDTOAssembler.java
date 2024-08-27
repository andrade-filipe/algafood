package com.esr.algafood.application.assembler.assemblers;

import com.esr.algafood.application.model.dto.CidadeDTO;
import com.esr.algafood.domain.entity.Cidade;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CidadeDTOAssembler {

    private ModelMapper modelMapper;

    public CidadeDTO toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    public List<CidadeDTO> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }
}
