package com.esr.algafood.jpa;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@AllArgsConstructor
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager entityManager;
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar(){
        return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    public Cozinha buscar(Long id){
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    public Cozinha adicionar(Cozinha cozinha){
        return entityManager.merge(cozinha);
    }

    @Transactional
    public void remover(Cozinha cozinha){
        cozinha = buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }

    public void remove(Cozinha cozinha){
        cozinhaRepository.deleteById(cozinha.getId());
    }
}
