package com.esr.algafood.application.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_DATA("/dados-invalidos", "Dados Inválidos"),
    SYSTEM_ERROR("/erro-do-sistema", "Erro de Sistema"),
    INVALID_PARAMETER("/parametro-invalido", "Parametro Inválio na URL"),
    REQUEST_NOT_READABLE("/requisição-não-processada", "Não foi Possível Processar a Requisição"),
    RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não Encontrado"),
    IS_BEING_USED("/entidade-em-uso", "Entidade Em Uso"),
    USER_EXCEPTION("/bad-request", "Erro do Usuário");

    private String title;
    private String uri;

    ProblemType(String path, String title ) {
        this.uri = "https://fakelink.com.br" + path;
        this.title = title;
    }
}
