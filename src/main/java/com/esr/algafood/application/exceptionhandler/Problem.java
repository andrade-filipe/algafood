package com.esr.algafood.application.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problem {

    private LocalDateTime dahaHora;

    private String mensagem;
}
