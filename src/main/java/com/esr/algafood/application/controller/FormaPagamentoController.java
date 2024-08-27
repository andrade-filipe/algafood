package com.esr.algafood.application.controller;

import com.esr.algafood.application.assembler.assemblers.FormaPagamentoDTOAssembler;
import com.esr.algafood.application.assembler.disassemblers.FormaPagamentoInputDisassembler;
import com.esr.algafood.application.model.dto.FormaPagamentoDTO;
import com.esr.algafood.application.model.input.FormaPagamentoInput;
import com.esr.algafood.domain.entity.FormaPagamento;
import com.esr.algafood.domain.repository.FormaPagamentoRepository;
import com.esr.algafood.domain.service.CadastroFormaPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
@AllArgsConstructor
public class FormaPagamentoController {

    private FormaPagamentoRepository formaPagamentoRepository;
    private CadastroFormaPagamentoService formaPagamentoService;
    private FormaPagamentoDTOAssembler formaPagamentoAssembler;
    private FormaPagamentoInputDisassembler formaPagamentoDisassembler;

    @GetMapping
    public List<FormaPagamentoDTO> listar() {
        List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();

        return formaPagamentoAssembler.toCollectionModel(todasFormasPagamentos);
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        return formaPagamentoAssembler.toModel(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = formaPagamentoService.salvar(formaPagamento);

        return formaPagamentoAssembler.toModel(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId,
                                         @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        formaPagamentoDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);

        return formaPagamentoAssembler.toModel(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }
}
