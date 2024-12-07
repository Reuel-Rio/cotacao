package com.cotacao.model;

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
    private Representante representante;

    @ManyToOne
    private Produto produto;

    private Double preco;
}