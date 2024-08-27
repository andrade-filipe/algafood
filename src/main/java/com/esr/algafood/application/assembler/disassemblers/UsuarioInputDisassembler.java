package com.esr.algafood.application.assembler.disassemblers;

import com.esr.algafood.application.model.input.UsuarioInput;
import com.esr.algafood.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioInputDisassembler {

    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInput usuarioInput){
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario){
        modelMapper.map(usuarioInput, usuario);
    }
}
