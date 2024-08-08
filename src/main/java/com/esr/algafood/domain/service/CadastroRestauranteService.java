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

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO
        = "Não existe um cadastro de restaurante com código %d";

    private RestauranteRepository restauranteRepository;
    private CadastroCozinhaService cadastroCozinha;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
    }
}
