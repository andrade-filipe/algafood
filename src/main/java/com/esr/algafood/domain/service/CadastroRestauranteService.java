package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Restaurante;
import com.esr.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroRestauranteService {

    private RestauranteRepository restauranteRepository;

    public Restaurante salvar(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }
}
