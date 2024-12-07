package com.cotacao.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produto {

	public Produto(String nome) {
		super();
		this.nome = nome;
	}
	
	public Produto() {
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;
}