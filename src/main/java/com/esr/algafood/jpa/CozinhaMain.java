package com.esr.algafood.jpa;

import com.esr.algafood.AlgafoodApplication;
import com.esr.algafood.domain.entity.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class CozinhaMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        /*Consulta*/
        List<Cozinha> cozinhas = cadastroCozinha.listar();

        for (Cozinha cozinha : cozinhas) {
            System.out.println(cozinha.getNome());
        }

        /*insert*/
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Japonesa");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Italiana");

        cadastroCozinha.adicionar(cozinha1);
        cadastroCozinha.adicionar(cozinha2);

        /*Busca*/
        Cozinha cozinha = cadastroCozinha.buscar(1L);

        /*Atualização*/

        Cozinha cozinhaEdit = new Cozinha();
        cozinhaEdit.setId(1L);
        cozinhaEdit.setNome("Francesa");
        cadastroCozinha.adicionar(cozinhaEdit);

        /*Delete*/

        cadastroCozinha.remove(cozinhaEdit);
    }
}
