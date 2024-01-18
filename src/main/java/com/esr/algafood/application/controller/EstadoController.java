package com.esr.algafood.application.controller;

import com.esr.algafood.domain.entity.Estado;
import com.esr.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/estados")
public class EstadoController {

    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }
}
