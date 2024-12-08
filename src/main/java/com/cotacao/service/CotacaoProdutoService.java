package com.cotacao.service;

import org.springframework.stereotype.Service;

import com.cotacao.model.CotacaoProduto;
import com.cotacao.repositories.CotacaoProdutoRepository;

import java.util.List;

@Service
public class CotacaoProdutoService {

    private final CotacaoProdutoRepository cotacaoProdutoRepository;

    public CotacaoProdutoService(CotacaoProdutoRepository cotacaoProdutoRepository) {
        this.cotacaoProdutoRepository = cotacaoProdutoRepository;
    }

    // Método para buscar cotações por representante
    public List<CotacaoProduto> buscarPorRepresentante(Long representanteId) {
        return cotacaoProdutoRepository.findByRepresentanteId(representanteId);
    }
    
    public void salvar(CotacaoProduto cotacaoProduto) {
        cotacaoProdutoRepository.save(cotacaoProduto);
    }
}
