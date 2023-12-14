package com.esr.algafood.aula219;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TipoDoNotificador {
    NivelUrgencia value();
}

//Como utilizar: @TipoDoNotificador(NivelUrgencia.URGENTE)
//Resolve: Ambiguidade de Beans
//Casos de uso: Mudar implementações de uma interface dentro
//de um construtor de uma classe que depende da interface.
