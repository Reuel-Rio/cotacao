package com.cotacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    // Página inicial
    @GetMapping("/")
    public String home() {
        return "redirect:/cotacoes/listar";  // Redireciona para a listagem de cotações
    }

    // Página de listagem de cotações
    @GetMapping("/cotacoes")
    public String listarCotasAtivas() {
        return "redirect:/cotacoes/listar";  // Redireciona para a página de cotações ativas
    }

    // Página para criar nova cotação
    @GetMapping("/cotacoes/criar")
    public String criarCotacao() {
        return "cotacoes/criar";  // Exibe o formulário para criar uma nova cotação
    }

    // Página para ver os detalhes da cotação
    @GetMapping("/cotacoes/{id}")
    public String visualizarCotacao() {
        return "mostrar";  // Exibe os detalhes de uma cotação
    }
}