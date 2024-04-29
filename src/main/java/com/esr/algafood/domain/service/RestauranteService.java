package com.esr.algafood.domain.service;

import com.esr.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestauranteService {

    private RestauranteRepository restauranteRepository;
}
