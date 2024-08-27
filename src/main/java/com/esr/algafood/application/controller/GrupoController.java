package com.esr.algafood.application.controller;

import com.esr.algafood.application.assembler.assemblers.GrupoDTOAssembler;
import com.esr.algafood.application.assembler.disassemblers.GrupoInputDisassembler;
import com.esr.algafood.application.model.dto.GrupoDTO;
import com.esr.algafood.application.model.input.GrupoInput;
import com.esr.algafood.domain.entity.Grupo;
import com.esr.algafood.domain.repository.GrupoRepository;
import com.esr.algafood.domain.service.CadastroGrupoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
@AllArgsConstructor
public class GrupoController {

    private GrupoRepository grupoRepository;
    private CadastroGrupoService cadastroGrupoService;
    private GrupoDTOAssembler grupoAssembler;
    private GrupoInputDisassembler grupoDisassembler;

    @GetMapping
    public List<GrupoDTO> listar(){
        List<Grupo> todosGrupos = grupoRepository.findAll();

        return grupoAssembler.toCollectionModel(todosGrupos);
    }

    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        return grupoAssembler.toModel(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoDisassembler.toDomainObject(grupoInput);

        grupo = cadastroGrupoService.salvar(grupo);

        return grupoAssembler.toModel(grupo);
    }

    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId,
                                @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);

        grupoDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = cadastroGrupoService.salvar(grupoAtual);

        return grupoAssembler.toModel(grupoAtual);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        cadastroGrupoService.excluir(grupoId);
    }
}
