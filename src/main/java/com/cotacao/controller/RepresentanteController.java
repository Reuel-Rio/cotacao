package com.cotacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cotacao.model.Representante;
import com.cotacao.repositories.RepresentanteRepository;
import com.cotacao.service.RepresentanteService;

import org.springframework.ui.Model;


@Controller
@RequestMapping
public class RepresentanteController {

	@Autowired
	private RepresentanteService representanteService;
	
	
	@GetMapping("/representantes/listar")
	public String listarRepresentantes(Model model) {
	    List<Representante> representantes = representanteService.buscarTodos();
	    model.addAttribute("representantes", representantes);
	    return "representantes/listar";
	}

}
