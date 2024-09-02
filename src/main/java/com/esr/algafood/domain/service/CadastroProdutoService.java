package com.esr.algafood.domain.service;

import com.esr.algafood.domain.entity.Produto;
import com.esr.algafood.domain.exception.NOT_FOUND.ProdutoNotFoundException;
import com.esr.algafood.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CadastroProdutoService {
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
            .orElseThrow(() -> new ProdutoNotFoundException(restauranteId, produtoId));
    }
}
