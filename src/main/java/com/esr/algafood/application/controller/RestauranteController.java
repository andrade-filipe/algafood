package com.esr.algafood.application.controller;

import com.esr.algafood.domain.entity.Restaurante;
import com.esr.algafood.domain.exception.EntityNotFoundException;
import com.esr.algafood.domain.repository.RestauranteRepository;
import com.esr.algafood.domain.service.CadastroRestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.esr.algafood.infrastructure.spec.RestauranteSpecs.*;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    private RestauranteRepository restauranteRepository;
    private CadastroRestauranteService restauranteService;

    @GetMapping()
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Optional<Restaurante>> buscar(@PathVariable Long restauranteId) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = restauranteService.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        try {
            Restaurante currRestaurante = restauranteRepository.findById(restauranteId).get();

            if (currRestaurante != null) {
                BeanUtils.copyProperties(restaurante, currRestaurante,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
                currRestaurante = restauranteService.salvar(currRestaurante);
                return ResponseEntity.ok(currRestaurante);
            }
            return ResponseEntity.notFound().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/teste")
    public List<Restaurante> teste(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
        return restauranteRepository.find(nome,taxaFreteInicial,taxaFreteFinal);
    }

    @GetMapping("/teste2")
    public List<Restaurante> restauranteComFreteGratis(String nome){

        return restauranteRepository.findComFreteGratis(nome);
    }
}
