package com.esr.algafood.application.controller;

import com.esr.algafood.domain.entity.Cidade;
import com.esr.algafood.domain.entity.Estado;
import com.esr.algafood.domain.entity.Restaurante;
import com.esr.algafood.domain.exception.EntityNotFoundException;
import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.repository.EstadoRepository;
import com.esr.algafood.domain.service.CadastroEstadoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/estados")
public class EstadoController {

    private EstadoRepository estadoRepository;
    private CadastroEstadoService estadoService;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
        try {
            estado = estadoService.salvar(estado);

            return ResponseEntity.status(HttpStatus.CREATED).body(estado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        try {
            Estado currEstado = estadoRepository.findById(estadoId).get();

            if (currEstado != null) {
                BeanUtils.copyProperties(estado, currEstado, "id");
                currEstado = estadoService.salvar(currEstado);
                return ResponseEntity.ok(currEstado);
            }
            return ResponseEntity.notFound().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId){
        try{
            estadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();

        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();

        }catch(IsBeingUsedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Recurso estadoId: " + estadoId + " está sendo utilizado");
        }
    }
}
