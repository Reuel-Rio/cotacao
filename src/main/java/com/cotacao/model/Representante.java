package com.cotacao.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Representante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private Double pedidoMinimo;

    @ManyToOne
    private Cotacao cotacao;
}