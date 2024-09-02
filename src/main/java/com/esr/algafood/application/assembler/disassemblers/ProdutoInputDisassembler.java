package com.esr.algafood.application.assembler.disassemblers;

import com.esr.algafood.application.model.input.ProdutoInput;
import com.esr.algafood.domain.entity.Produto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProdutoInputDisassembler {

    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInput produtoInput){
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto){
        modelMapper.map(produtoInput, produto);
    }
}
