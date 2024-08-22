package com.esr.algafood.application.controller;

import com.esr.algafood.application.assembler.assemblers.EstadoDTOAssembler;
import com.esr.algafood.application.assembler.disassemblers.EstadoInputDisassembler;
import com.esr.algafood.application.model.dto.EstadoDTO;
import com.esr.algafood.application.model.input.EstadoInput;
import com.esr.algafood.domain.entity.Estado;
import com.esr.algafood.domain.exception.NOT_FOUND.EntityNotFoundException;
import com.esr.algafood.domain.repository.EstadoRepository;
import com.esr.algafood.domain.service.CadastroEstadoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/estados")
public class EstadoController {

    private EstadoRepository estadoRepository;
    private CadastroEstadoService estadoService;
    private EstadoDTOAssembler estadoDTOAssembler;
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoDTO> listar(){
        return estadoDTOAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        return estadoDTOAssembler.toModel(estadoService.buscarOuFalhar(estadoId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        estado = estadoRepository.save(estado);
        return estadoDTOAssembler.toModel(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@PathVariable Long estadoId,
                            @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);
        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
        estadoAtual = estadoRepository.save(estadoAtual);
        return estadoDTOAssembler.toModel(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        estadoService.excluir(estadoId);
    }

}
