package com.cotacao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cotacao.model.Representante;

import java.util.List;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {
    List<Representante> findByCotacaoId(Long cotacaoId);
}