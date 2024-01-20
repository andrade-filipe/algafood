package com.esr.algafood.application.controller;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.repository.CozinhaRepository;
import com.esr.algafood.representation.model.xml.CozinhaXml;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public Optional<Cozinha> buscar(@PathVariable Long cozinhaId){
        return cozinhaRepository.findById(cozinhaId);
    }
}
