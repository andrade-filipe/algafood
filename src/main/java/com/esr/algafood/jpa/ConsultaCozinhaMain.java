package com.esr.algafood.jpa;

import com.esr.algafood.AlgafoodApplication;
import com.esr.algafood.domain.entity.Cozinha;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        List<Cozinha> cozinhas = cadastroCozinha.listar();

        for (Cozinha cozinha : cozinhas) {
            System.out.println(cozinha.getNome());
        }
    }
}
