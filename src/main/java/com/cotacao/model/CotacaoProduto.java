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
    @JoinColumn(name = "cotacao_id")
    @JsonIgnore
    private Cotacao cotacao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonIgnore
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name = "representante_id", nullable = false)
    @JsonIgnore
    private Representante representante;
}