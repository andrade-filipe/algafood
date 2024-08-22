package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Estado;
import com.esr.algafood.domain.exception.NOT_FOUND.EstadoNotFoundException;
import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CadastroEstadoService {

    private static final String MSG_ESTADO_EM_USO  =
        "Estado de código %d não pode ser removido, pois está em uso";

    private EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    @Transactional
    public void excluir(Long estadoId){
        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNotFoundException(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new IsBeingUsedException(
                String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId)
            .orElseThrow(() -> new EstadoNotFoundException(estadoId));
    }
}
