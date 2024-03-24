package com.esr.algafood.application.controller;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.repository.CozinhaRepository;
import com.esr.algafood.representation.model.xml.CozinhaXml;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.*;

@AllArgsConstructor
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaXml listarXml(){
        return new CozinhaXml(cozinhaRepository.findAll());
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
        return cozinhaRepository.save(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        try{
            Cozinha currCozinha = cozinhaRepository.findById(cozinhaId).get();
            BeanUtils.copyProperties(cozinha, currCozinha, "id");
            cozinhaRepository.save(currCozinha);
            return ResponseEntity.ok(currCozinha);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
