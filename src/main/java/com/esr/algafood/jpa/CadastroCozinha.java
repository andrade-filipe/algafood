package com.esr.algafood.jpa;

import com.esr.algafood.domain.entity.Cozinha;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listar(){
        return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }
}
