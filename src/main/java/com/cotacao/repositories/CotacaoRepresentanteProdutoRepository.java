package com.cotacao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cotacao.model.CotacaoRepresentanteProduto;

import java.util.List;

public interface CotacaoRepresentanteProdutoRepository extends JpaRepository<CotacaoRepresentanteProduto, Long> {
    List<CotacaoRepresentanteProduto> findByCotacaoIdAndRepresentanteId(Long cotacaoId, Long representanteId);
}