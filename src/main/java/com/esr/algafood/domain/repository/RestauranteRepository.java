package com.esr.algafood.domain.repository;

import com.esr.algafood.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository
    extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
    JpaSpecificationExecutor<Restaurante> {
    @Query("from Restaurante r join r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAll();
    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);

//    @Query("from Restaurante where nome like %:nome%")
    List<Restaurante> testandoQuery(String nome);
}
