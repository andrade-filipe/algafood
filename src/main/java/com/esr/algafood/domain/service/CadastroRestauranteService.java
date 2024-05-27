package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.entity.Restaurante;
import com.esr.algafood.domain.exception.EntityNotFoundException;
import com.esr.algafood.domain.repository.CozinhaRepository;
import com.esr.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CadastroRestauranteService {

    private RestauranteRepository restauranteRepository;
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("id: %d not found in database", cozinhaId)));

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }
}
