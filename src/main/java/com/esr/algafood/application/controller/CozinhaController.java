package com.esr.algafood.application.controller;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }
}
