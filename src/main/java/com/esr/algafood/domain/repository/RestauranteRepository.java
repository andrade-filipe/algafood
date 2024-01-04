package com.esr.algafood.domain.repository;

import com.esr.algafood.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
