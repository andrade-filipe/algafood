package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.exception.NOT_FOUND.CozinhaNotFoundException;
import com.esr.algafood.domain.exception.NOT_FOUND.EntityNotFoundException;
import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroCozinhaService {

    private static final String MSG_COZINHA_EM_USO = "Id: %d is being used";
    private static final String MSG_COZINHA_NAO_ENCONTRADA = "Id: %d was not found in the database";


    private CozinhaRepository cozinhaRepository;
    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId){
        try{
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e){
            throw new CozinhaNotFoundException(cozinhaId);
        } catch (DataIntegrityViolationException e){
            throw new IsBeingUsedException(
                String.format(MSG_COZINHA_EM_USO, cozinhaId)
            );
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId){
        return cozinhaRepository.findById(cozinhaId)
            .orElseThrow(() -> new CozinhaNotFoundException(cozinhaId));
    }
}
