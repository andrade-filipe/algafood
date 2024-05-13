package com.esr.algafood.application.controller;

import com.esr.algafood.domain.entity.Cidade;
import com.esr.algafood.domain.entity.Estado;
import com.esr.algafood.domain.exception.EntityNotFoundException;
import com.esr.algafood.domain.repository.CidadeRepository;
import com.esr.algafood.domain.service.CadastroCidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository cidadeRepository;
    private CadastroCidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = cidadeService.salvar(cidade);

            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
