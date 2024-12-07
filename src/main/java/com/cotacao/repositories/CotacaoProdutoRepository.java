package com.cotacao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cotacao.enums.Status;
import com.cotacao.model.Cotacao;
import com.cotacao.model.CotacaoProduto;

import java.util.List;

@Repository
public interface CotacaoProdutoRepository extends JpaRepository<CotacaoProduto, Long> {
    List<CotacaoProduto> findByCotacaoId(Long cotacaoId);
    
    @Query("SELECT c FROM Cotacao c JOIN FETCH c.cotacaoProdutos WHERE c.status = :status")
    List<Cotacao> findAllWithCotacaoProdutos(@Param("status") Status status);
}