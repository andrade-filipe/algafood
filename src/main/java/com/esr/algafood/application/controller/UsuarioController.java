package com.esr.algafood.application.controller;

import com.esr.algafood.application.assembler.assemblers.UsuarioDTOAssembler;
import com.esr.algafood.application.assembler.disassemblers.UsuarioInputDisassembler;
import com.esr.algafood.application.model.dto.UsuarioDTO;
import com.esr.algafood.application.model.input.SenhaInput;
import com.esr.algafood.application.model.input.UsuarioComSenhaInput;
import com.esr.algafood.application.model.input.UsuarioInput;
import com.esr.algafood.domain.entity.Usuario;
import com.esr.algafood.domain.repository.UsuarioRepository;
import com.esr.algafood.domain.service.CadastroUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final CadastroUsuarioService cadastroUsuarioService;
    private UsuarioRepository usuarioRepository;
    private CadastroUsuarioService usuarioService;
    private UsuarioDTOAssembler usuarioDTOAssembler;
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public List<UsuarioDTO> listar(){
        return usuarioDTOAssembler.toCollectionModel(usuarioRepository.findAll());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscar(@PathVariable Long usuarioId){
        return usuarioDTOAssembler.toModel(usuarioService.buscarOuFalhar(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput){

        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        usuario = cadastroUsuarioService.salvar(usuario);

        return usuarioDTOAssembler.toModel(usuario);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@PathVariable Long usuarioId,
                                  @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuarioService.salvar(usuarioAtual);

        return usuarioDTOAssembler.toModel(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        cadastroUsuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
