package com.esr.algafood;

import com.esr.algafood.domain.entity.Cozinha;
import com.esr.algafood.domain.exception.IsBeingUsedException;
import com.esr.algafood.domain.exception.NOT_FOUND.CozinhaNotFoundException;
import com.esr.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CadastroCozinhaIT {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    @DisplayName("GIVEN a Cozinha WHEN registering SHOULD be successful ")
    public void CadastroCozinhaComSucesso(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Cozinha");

        cozinha = cadastroCozinha.salvar(cozinha);

        assertThat(cozinha).isNotNull();
        assertThat(cozinha.getId()).isNotNull();
    }

    @Test
    @DisplayName("SHOULD fail WHEN Cozinha.nome is null WHILE inserting")
    public void CozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        ConstraintViolationException erroEsperado =
            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                cadastroCozinha.salvar(novaCozinha);
            });

        assertThat(erroEsperado).isNotNull();
    }

    @Test
    @DisplayName("SHOULD fail WHEN trying to delete a Cozinha in conflict")
    public void CozinhaEmUso(){
        IsBeingUsedException erroEsperado =
            Assertions.assertThrows(IsBeingUsedException.class, () -> {
                cadastroCozinha.excluir(1L);
            });

        assertThat(erroEsperado).isNotNull();
    }

    @Test
    @DisplayName("SHOULD fail WHEN trying to delete a non existent Cozinha")
    public void CozinhaInexistente(){
        CozinhaNotFoundException erroEsperado =
            Assertions.assertThrows(CozinhaNotFoundException.class, () -> {
                cadastroCozinha.excluir(100L);
            });

        assertThat(erroEsperado).isNotNull();
    }

}
