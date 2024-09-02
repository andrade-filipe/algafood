package com.esr.algafood.application.assembler.assemblers;

import com.esr.algafood.application.model.dto.ProdutoDTO;
import com.esr.algafood.domain.entity.Produto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProdutoDTOAssembler {
    private ModelMapper modelMapper;

    public ProdutoDTO toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }
}
