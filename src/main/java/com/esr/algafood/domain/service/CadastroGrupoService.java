package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Grupo;
import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.exception.NOT_FOUND.GrupoNotFoundException;
import com.esr.algafood.domain.repository.GrupoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CadastroGrupoService {

    private static final String MSG_GRUPO_EM_USO
        = "Grupo de código %d não pode ser removido, pois está em uso";

    private GrupoRepository grupoRepository;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long grupoId) {
        try {
            grupoRepository.deleteById(grupoId);
            grupoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNotFoundException(grupoId);

        } catch (DataIntegrityViolationException e) {
            throw new IsBeingUsedException(
                String.format(MSG_GRUPO_EM_USO, grupoId));
        }
    }

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId)
            .orElseThrow(() -> new GrupoNotFoundException(grupoId));
    }
}
