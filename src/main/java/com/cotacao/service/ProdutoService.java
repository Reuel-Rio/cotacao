package com.cotacao.service;

import org.springframework.stereotype.Service;

import com.cotacao.model.Produto;
import com.cotacao.repositories.ProdutoRepository;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto saveOrFindProduto(String nome) {
        Optional<Produto> existingProduto = produtoRepository.findByNomeIgnoreCase(nome.toUpperCase());
        if (existingProduto.isPresent()) {
            return existingProduto.get();
        }
        Produto novoProduto = new Produto();
        novoProduto.setNome(nome.toUpperCase());
        return produtoRepository.save(novoProduto);
    }
}
