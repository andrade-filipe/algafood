package com.esr.algafood.application.controller;

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

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @GetMapping("/by-nome")
    public List<Cozinha> cozinhaPorNome(@RequestParam("nome") String nome){
        return cozinhaRepository.findByNomeContaining(nome);
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId){
        return cozinhaService.buscarOuFalhar(cozinhaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha){
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        Cozinha currCozinha = cozinhaService.buscarOuFalhar(cozinhaId);

        BeanUtils.copyProperties(cozinha, currCozinha, "id");

        return cozinhaService.salvar(currCozinha);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId){
        cozinhaService.excluir(cozinhaId);
    }
}
