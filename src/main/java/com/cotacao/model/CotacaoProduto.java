package com.cotacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CotacaoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cotacao_id", nullable = false)
    @JsonIgnore
    private Cotacao cotacao;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name = "representante_id", nullable = true)
    @JsonIgnore
    private Representante representante;
}