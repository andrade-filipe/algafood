package com.esr.algafood.application.assembler.assemblers;

import com.esr.algafood.application.model.dto.UsuarioDTO;
import com.esr.algafood.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsuarioDTOAssembler {
    private ModelMapper modelMapper;

    public UsuarioDTO toModel(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> toCollectionModel(List<Usuario> usuarios) {
        return usuarios.stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }
}
