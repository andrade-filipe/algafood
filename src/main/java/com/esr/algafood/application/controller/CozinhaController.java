package com.esr.algafood.application.controller;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.exception.EntityNotFoundException;
import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.repository.CozinhaRepository;
import com.esr.algafood.domain.service.CadastroCozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Optional<Cozinha>> buscar(@PathVariable Long cozinhaId){
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

        if(cozinha.isPresent()){
            return ResponseEntity.ok(cozinha);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        try{
            Cozinha currCozinha = cozinhaRepository.findById(cozinhaId).get();
            BeanUtils.copyProperties(cozinha, currCozinha, "id");
            cozinhaService.salvar(currCozinha);
            return ResponseEntity.ok(currCozinha);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
        try{
            cozinhaService.excluir(cozinhaId);
            return ResponseEntity.noContent().build();

        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();

        }catch(IsBeingUsedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
