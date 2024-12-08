package com.cotacao.DTO;

import java.util.List;

import lombok.Data;


@Data
public class CotacaoDTO {
    private String nomeCotacao;
    private List<String> nomesProdutos;

    // Getters e Setters
}
