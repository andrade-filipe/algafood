package com.esr.algafood.application.controller;


import com.esr.algafood.application.assembler.assemblers.ProdutoDTOAssembler;
import com.esr.algafood.application.assembler.disassemblers.ProdutoInputDisassembler;
import com.esr.algafood.application.model.dto.ProdutoDTO;
import com.esr.algafood.application.model.input.ProdutoInput;
import com.esr.algafood.domain.entity.Produto;
import com.esr.algafood.domain.entity.Restaurante;
import com.esr.algafood.domain.repository.ProdutoRepository;
import com.esr.algafood.domain.service.CadastroProdutoService;
import com.esr.algafood.domain.service.CadastroRestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

    private ProdutoRepository produtoRepository;
    private CadastroProdutoService produtoService;
    private CadastroRestauranteService restauranteService;
    private ProdutoDTOAssembler produtoDTOAssembler;
    private ProdutoInputDisassembler produtoInputDisassembler;

    @GetMapping
    public List<ProdutoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        List<Produto> todosProdutos = produtoRepository.findByRestaurante(restaurante);

        return produtoDTOAssembler.toCollectionModel(todosProdutos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        return produtoDTOAssembler.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO adicionar(@PathVariable Long restauranteId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtoService.salvar(produto);

        return produtoDTOAssembler.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = produtoService.salvar(produtoAtual);

        return produtoDTOAssembler.toModel(produtoAtual);
    }

}
