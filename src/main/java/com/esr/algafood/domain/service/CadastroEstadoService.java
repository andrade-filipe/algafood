package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Estado;
import com.esr.algafood.domain.exception.EntityNotFoundException;
import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroEstadoService {

    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId){
        try{
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                String.format("Id: %d was not found in the database", estadoId)
            );
        } catch (DataIntegrityViolationException e){
            throw new IsBeingUsedException(
                String.format("Id: %d is being used", estadoId)
            );
        }
    }
}
