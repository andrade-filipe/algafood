package com.esr.algafood.application.assembler.assemblers;

import com.esr.algafood.application.model.dto.FormaPagamentoDTO;
import com.esr.algafood.domain.entity.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    public List<FormaPagamentoDTO> toCollectionModel(List<FormaPagamento> formasPagamentos) {
        return formasPagamentos.stream()
            .map(formaPagamento -> toModel(formaPagamento))
            .collect(Collectors.toList());
    }
}
