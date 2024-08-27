package com.esr.algafood.application.assembler.disassemblers;

import com.esr.algafood.application.model.input.GrupoInput;
import com.esr.algafood.domain.entity.Grupo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GrupoInputDisassembler {
    private ModelMapper modelMapper;

    public Grupo toDomainObject(GrupoInput grupoInput){
        return modelMapper.map(grupoInput, Grupo.class);
    }

    public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo){
        modelMapper.map(grupoInput, grupo);
    }
}
