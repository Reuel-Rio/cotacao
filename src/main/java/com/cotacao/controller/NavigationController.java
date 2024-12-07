package com.cotacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    // Página inicial - Redireciona para a lista de cotações
    @GetMapping("/")
    public String home() {
        return "redirect:/cotacoes/listar";
    }

    // Página de listagem de cotações
    @GetMapping("/cotacoes/listar")
    public String listarCotasAtivas() {
        return "listar"; // Página que exibe a lista de cotações
    }

    // Página para criar nova cotação
    @GetMapping("/cotacoes/criar")
    public String criarCotacao() {
        return "criar"; // Página com formulário para criar uma nova cotação
    }

    // Página para visualizar detalhes de uma cotação
    @GetMapping("/cotacoes/detalhes")
    public String visualizarCotacao() {
        return "mostrar"; // Página que exibe os detalhes de uma cotação
    }
}
