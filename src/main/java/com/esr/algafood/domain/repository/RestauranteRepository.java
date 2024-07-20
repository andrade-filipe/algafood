package com.esr.algafood.domain.repository;

import com.esr.algafood.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);

//    @Query("from Restaurante where nome like %:nome%")
    List<Restaurante> testandoQuery(String nome);
}
