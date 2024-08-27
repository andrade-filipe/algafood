package com.esr.algafood.application.controller;

import com.esr.algafood.application.assembler.disassemblers.RestauranteInputDisassembler;
import com.esr.algafood.application.assembler.disassemblers.RestauranteModelAssembler;
import com.esr.algafood.application.model.dto.RestauranteDTO;
import com.esr.algafood.application.model.input.RestauranteInput;
import com.esr.algafood.domain.entity.Restaurante;
import com.esr.algafood.domain.exception.NOT_FOUND.CidadeNotFoundException;
import com.esr.algafood.domain.exception.NOT_FOUND.CozinhaNotFoundException;
import com.esr.algafood.domain.exception.NegocioException;
import com.esr.algafood.domain.exception.ValidationException;
import com.esr.algafood.domain.repository.RestauranteRepository;
import com.esr.algafood.domain.service.CadastroCidadeService;
import com.esr.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    private RestauranteRepository restauranteRepository;
    private CadastroRestauranteService restauranteService;
    private SmartValidator smartValidator;
    private RestauranteModelAssembler restauranteAssembler;
    private RestauranteInputDisassembler restauranteDisassembler;

    @GetMapping()
    public List<RestauranteDTO> listar() {
        return restauranteAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        return restauranteAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar( @RequestBody @Valid RestauranteInput restauranteInput) {
        try{
            Restaurante restaurante = restauranteDisassembler.toDomainObject(restauranteInput);
            return restauranteAssembler.toModel(restauranteService.salvar(restaurante));
        } catch (CozinhaNotFoundException | CidadeNotFoundException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteDTO atualizar(@PathVariable Long restauranteId,
                                    @RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

        restauranteDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

        try {
            return restauranteAssembler.toModel(restauranteService.salvar(restauranteAtual));
        }catch (CozinhaNotFoundException | CidadeNotFoundException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long restauranteId) {
        restauranteService.desativar(restauranteId);
    }

    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(restaurante, objectName);

        smartValidator.validate(restaurante, errors);

        if(errors.hasErrors()) {
            throw new ValidationException(errors);

        }
    }

    private void merge(Map<String, Object> dadosOrigem,
                       Restaurante restauranteDestino,
                       HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }

    }

    @GetMapping("/teste")
    public List<Restaurante> teste(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
        return restauranteRepository.find(nome,taxaFreteInicial,taxaFreteFinal);
    }

    @GetMapping("/teste2")
    public List<Restaurante> restauranteComFreteGratis(String nome){

        return restauranteRepository.findComFreteGratis(nome);
    }

    // DEPRECATED
//    @PatchMapping("/{restauranteId}")
//    public RestauranteDTO atualizarParcial(@PathVariable Long restauranteId,
//                                        @RequestBody Map<String, Object> campos,
//                                        HttpServletRequest request) {
//        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
//
//        merge(campos, restauranteAtual, request);
//        validate(restauranteAtual, "restaurante");
//
//        return atualizar(restauranteId, restauranteAtual);
//    }
}
