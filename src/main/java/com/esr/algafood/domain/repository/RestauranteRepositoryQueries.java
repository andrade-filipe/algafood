package com.esr.algafood.domain.repository;

import com.esr.algafood.domain.entity.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    /*RestauranteRepositoryImpl -> implementação customizada*/
    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> findComFreteGratis(String nome);
}
