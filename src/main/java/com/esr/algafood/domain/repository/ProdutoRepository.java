package com.esr.algafood.domain.repository;

import com.esr.algafood.domain.entity.Produto;
import com.esr.algafood.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("FROM Produto WHERE restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findById(
        @Param("restaurante") Long restauranteId,
        @Param("produto") Long produto
    );

    List<Produto> findByRestaurante(Restaurante restaurante);
}
