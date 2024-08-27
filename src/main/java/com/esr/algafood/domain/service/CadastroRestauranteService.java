package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.entity.Restaurante;
import com.esr.algafood.domain.exception.NOT_FOUND.EntityNotFoundException;
import com.esr.algafood.domain.exception.NOT_FOUND.RestauranteNotFoundException;
import com.esr.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CadastroRestauranteService {

    private RestauranteRepository restauranteRepository;
    private CadastroCozinhaService cadastroCozinha;

    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {

        return restauranteRepository.findById(restauranteId)
            .orElseThrow(() -> new RestauranteNotFoundException(restauranteId));
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.ativar();
    }

    @Transactional
    public void desativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);

        restaurante.desativar();
    }
}
