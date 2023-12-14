package com.esr.algafood.aulas.aula225;

public class ValueOfProperties{

    @Value("${notificador.email.host-servidor}") //de application.properties
    private String host;

    @Value("${notificador.email.porta-servidor}") //de application.properties
    private Integer porta;
}
