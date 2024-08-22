package com.esr.algafood.application.controller;

import com.esr.algafood.application.assembler.assemblers.CidadeDTOAssembler;
import com.esr.algafood.application.assembler.disassemblers.CidadeInputDisassembler;
import com.esr.algafood.application.exceptionhandler.Problem;
import com.esr.algafood.application.model.dto.CidadeDTO;
import com.esr.algafood.application.model.input.CidadeInput;
import com.esr.algafood.domain.entity.Cidade;
import com.esr.algafood.domain.exception.NOT_FOUND.EntityNotFoundException;
import com.esr.algafood.domain.exception.NOT_FOUND.EstadoNotFoundException;
import com.esr.algafood.domain.exception.NegocioException;
import com.esr.algafood.domain.repository.CidadeRepository;
import com.esr.algafood.domain.service.CadastroCidadeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository cidadeRepository;
    private CadastroCidadeService cidadeService;
    private CidadeDTOAssembler cidadeDTOAssembler;
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public List<CidadeDTO> listar(){
        return cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAll());
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        return cidadeDTOAssembler.toModel(cidadeService.buscarOuFalhar(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try{
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            cidade = cidadeRepository.save(cidade);
            return cidadeDTOAssembler.toModel(cidade);
        }catch (EstadoNotFoundException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId,
                            @RequestBody @Valid CidadeInput cidadeInput) {
        try{
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cidadeService.salvar(cidadeAtual);

            return cidadeDTOAssembler.toModel(cidadeAtual);
        } catch (EstadoNotFoundException e){
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }
}
