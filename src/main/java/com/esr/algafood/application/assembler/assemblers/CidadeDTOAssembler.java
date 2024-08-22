package com.esr.algafood.application.assembler.assemblers;

import com.esr.algafood.application.model.dto.CidadeDTO;
import com.esr.algafood.domain.entity.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeDTO toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    public List<CidadeDTO> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
            .map(cidade -> toModel(cidade))
            .collect(Collectors.toList());
    }
}
