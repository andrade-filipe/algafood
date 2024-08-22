package com.esr.algafood.application.controller;

import com.esr.algafood.application.assembler.assemblers.CozinhaDTOAssembler;
import com.esr.algafood.application.assembler.disassemblers.CozinhaInputDisassembler;
import com.esr.algafood.application.model.dto.CozinhaDTO;
import com.esr.algafood.application.model.input.CozinhaInput;
import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.repository.CozinhaRepository;
import com.esr.algafood.domain.service.CadastroCozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;
    private CadastroCozinhaService cozinhaService;
    private CozinhaDTOAssembler cozinhaDTOAssembler;
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @GetMapping
    public List<CozinhaDTO> listar(){
        return cozinhaDTOAssembler.toCollectionModel(cozinhaRepository.findAll());
    }

    @GetMapping("/by-nome")
    public List<CozinhaDTO> cozinhaPorNome(@RequestParam("nome") String nome){
        return cozinhaDTOAssembler.toCollectionModel(cozinhaRepository.findByNomeContaining(nome));
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId){
        return cozinhaDTOAssembler.toModel(cozinhaService.buscarOuFalhar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO adicionar(@RequestBody @Valid CozinhaInput cozinhaInput){
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        cozinha = cozinhaService.salvar(cozinha);
        return cozinhaDTOAssembler.toModel(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId,
                                @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

        return cozinhaDTOAssembler.toModel(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId){
        cozinhaService.excluir(cozinhaId);
    }
}
