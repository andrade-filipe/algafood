package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.exception.EntityNotFoundException;
import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroCozinhaService {

    private CozinhaRepository cozinhaRepository;
    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId){
        try{
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                String.format("Id: %d was not found in the database", cozinhaId)
            );
        } catch (DataIntegrityViolationException e){
            throw new IsBeingUsedException(
                String.format("Id: %d is being used", cozinhaId)
            );
        }
    }
}
