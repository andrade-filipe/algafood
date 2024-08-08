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

    private static final String MSG_CIDADE_EM_USO
        = "Cidade de código %d não pode ser removida, pois está em uso";

    private static final String MSG_CIDADE_NAO_ENCONTRADA
        = "Não existe um cadastro de cidade com código %d";

    private CidadeRepository cidadeRepository;

    private CadastroEstadoService cadastroEstado;

    public Cidade salvar(Cidade cidade){
        Long estadoId = cidade.getEstado().getId();

        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId){
        try {
            cidadeRepository.deleteById(cidadeId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));

        } catch (DataIntegrityViolationException e) {
            throw new IsBeingUsedException(
                String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
    }
}
