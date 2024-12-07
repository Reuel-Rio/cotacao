package com.cotacao.service;

import org.springframework.stereotype.Service;

import com.cotacao.model.Representante;
import com.cotacao.repositories.RepresentanteRepository;

@Service
public class RepresentanteService {

    private final RepresentanteRepository representanteRepository;

    public RepresentanteService(RepresentanteRepository representanteRepository) {
        this.representanteRepository = representanteRepository;
    }

    public Representante salvarRepresentante(String nome, Double pedidoMinimo) {
        Representante representante = new Representante();
        representante.setNome(nome);
        representante.setPedidoMinimo(pedidoMinimo);
        return representanteRepository.save(representante);
    }
}