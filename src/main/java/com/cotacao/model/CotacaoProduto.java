package com.cotacao.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CotacaoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cotacao_id")
    private Cotacao cotacao;

    @ManyToOne
    private Produto produto;
}