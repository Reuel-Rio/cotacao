package com.cotacao.service;

import org.springframework.stereotype.Service;

import com.cotacao.model.CotacaoProduto;
import com.cotacao.model.Representante;
import com.cotacao.repositories.CotacaoProdutoRepository;

import jakarta.transaction.Transactional;

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
    	System.out.println("Salvando CotacaoProduto: " + cotacaoProduto);
        cotacaoProdutoRepository.save(cotacaoProduto);
    }
    
    @Transactional
    public void atualizarRepresentante(Long cotacaoProdutoId, Representante representante) {
        CotacaoProduto cotacaoProduto = cotacaoProdutoRepository.findById(cotacaoProdutoId)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado na cotação"));
        cotacaoProduto.setRepresentante(representante);
        cotacaoProdutoRepository.save(cotacaoProduto);
    }

}
