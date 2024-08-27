package com.esr.algafood.domain.repository;

import com.esr.algafood.domain.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
}
