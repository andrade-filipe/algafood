package com.esr.algafood.application.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    REQUEST_NOT_READABLE("/requisição-não-processada", "Não foi Possível Processar a Requisição"),
    ENTITY_NOT_FOUND("/entidade-nao-encontrada", "Entidade não Encontrada"),
    IS_BEING_USED("/entidade-em-uso", "Entidade Em Uso"),
    USER_EXCEPTION("/bad-request", "Erro do Usuário");


    private String title;
    private String uri;

    ProblemType(String path, String title ) {
        this.uri = "https://fakelink.com.br" + path;
        this.title = title;
    }
}
