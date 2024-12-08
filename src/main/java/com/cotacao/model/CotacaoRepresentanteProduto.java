package com.cotacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CotacaoRepresentanteProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cotacao cotacao;

    @ManyToOne
    @JsonIgnore
    private Representante representante;

    @ManyToOne
    @JsonIgnore
    private Produto produto;

    private Double preco;
}