package com.cotacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cotacao.model.CotacaoProduto;
import com.cotacao.service.CotacaoService;

@Controller
public class NavigationController {

	@Autowired
	private CotacaoService cotacaoService;

	// Página inicial - Redireciona para a lista de cotações
	@GetMapping("/")
	public String home() {
		System.out.println("API: / ");
		System.out.println("HOME: / redirect:/cotacoes/listar");
		return "redirect:/cotacoes/listar";
	}

	// Página de listagem de cotações
	@GetMapping("/cotacoes/listar")
	public String listarCotasAtivas() {
		System.out.println("API: /cotacoes/listar ");
		System.out.println("listarCotasAtivas: / listar");
		return "listar"; // Página que exibe a lista de cotações
	}

	// Página para criar nova cotação
	@GetMapping("/cotacoes/criar")
	public String criarCotacao() {
		System.out.println("API: /cotacoes/criar ");
		System.out.println("criarCotacao: / criar");
		return "criar"; // Página com formulário para criar uma nova cotação
	}

	// Página para visualizar detalhes de uma cotação
	@GetMapping("/cotacoes/detalhes")
	public String visualizarCotacao() {
		System.out.println("API: /cotacoes/detalhes ");
		System.out.println("visualizarCotacao: / mostrar");
		return "mostrar"; // Página que exibe os detalhes de uma cotação
	}

	@GetMapping("/error")
	public String visualizarERROR() {

		System.out.println("visualizarERROR: / error");
		return "error"; // Página que exibe os detalhes de uma cotação
	}

	/**
	 * Retorna as cotações associadas a um representante específico.
	 * 
	 * @param id Identificador do representante
	 * @return Lista de produtos associados ao representante
	 */
	@GetMapping("/represent/{id}")
	public List<CotacaoProduto> listarCotacoesPorRepresentante(@PathVariable Long id) {
		System.out.println("LISTAR COTAÇÕES POR REPRESENTANTE: ID = " + id);
		return cotacaoService.listarPorRepresentante(id);
	}
}
