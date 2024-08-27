package com.esr.algafood.application.model.input;

import com.esr.algafood.application.model.dto.CidadeShortDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private CidadeIdInput cidade;
}
