package com.esr.algafood.application.controller;

import com.esr.algafood.domain.entity.Restaurante;
import com.esr.algafood.domain.repository.RestauranteRepository;
import com.esr.algafood.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    private RestauranteRepository restauranteRepository;
    private RestauranteService restauranteService;

    @GetMapping()
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Optional<Restaurante>> buscar(@PathVariable Long restauranteId){
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

        if(restaurante.isPresent()){
            return ResponseEntity.ok(restaurante);
        }

        return ResponseEntity.notFound().build();
    }
}
