package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Cidade;
import com.esr.algafood.domain.entity.Estado;
import com.esr.algafood.domain.exception.EntityNotFoundException;
import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.repository.CidadeRepository;
import com.esr.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroCidadeService {
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade){
        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId){
        try{
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                String.format("Id: %d was not found in the database", cidadeId)
            );
        } catch (DataIntegrityViolationException e){
            throw new IsBeingUsedException(
                String.format("Id: %d is being used", cidadeId)
            );
        }
    }
}
