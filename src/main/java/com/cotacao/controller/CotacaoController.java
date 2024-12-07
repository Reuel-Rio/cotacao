package com.cotacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cotacao.model.Cotacao;
import com.cotacao.repositories.CotacaoRepository;
import com.cotacao.service.CotacaoService;
import com.cotacao.service.ExcelService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @Autowired
    private ExcelService excelService;
    
    @Autowired
    private CotacaoRepository cotacaoRepository;

    // Página para o lojista criar a cotação
    @GetMapping("/criar")
    public String exibirFormulario(Model model) {
        model.addAttribute("cotacao", new Cotacao());
        return "criar-cotacao";
    }

    public CotacaoController(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }
    
    @GetMapping("/listar")
    public List<Cotacao> listarCotacoes() {
    	System.out.println("LISTAR");
        return cotacaoService.listarCotasAtivas();
    }
    
    @GetMapping("/cotacoes/{id}")
    public String visualizarCotacao(@PathVariable Long id, Model model) {
        Optional<Cotacao> cotacao = cotacaoRepository.findById(id);
        if (cotacao.isPresent()) {
            model.addAttribute("cotacao", cotacao.get());
            return "mostrar";
        } else {
            // Redireciona ou lança erro se a cotação não for encontrada
            return "redirect:/cotacoes/listar";
        }
    }
    
    // Submeter os produtos para cotação
    @PostMapping("/criar")
    public String submeterCotacao(@RequestParam("nomeCotacao") String nomeCotacao,
                                   @RequestParam("nomesProdutos") List<String> nomesProdutos, 
                                   Model model) {
        Cotacao novaCotacao = cotacaoService.criarCotacao(nomeCotacao, nomesProdutos);
        model.addAttribute("sucesso", "Cotação salva com sucesso!");
        return "redirect:/cotacao/criar";
    }

    // Gerar o arquivo Excel para o representante preencher
    @PostMapping("/gerarExcel")
    public String gerarExcel(@RequestParam("nomeCotacao") String nomeCotacao,
                             @RequestParam("nomeRepresentante") String nomeRepresentante,
                             @RequestParam("pedidoMinimo") Double pedidoMinimo,
                             Model model) {
        try {
            List<Cotacao> cotacoes = cotacaoService.listarCotasAtivas();  // Busca cotações ativas
            String caminhoArquivo = "caminho/para/gerar/cotacao_" + nomeCotacao + ".xlsx";
            excelService.gerarExcel(cotacoes, nomeCotacao, nomeRepresentante, pedidoMinimo, caminhoArquivo);
            model.addAttribute("sucesso", "Arquivo Excel gerado com sucesso!");
        } catch (IOException e) {
            model.addAttribute("erro", "Erro ao gerar o arquivo Excel.");
        }
        return "redirect:/cotacao/criar";
    }
    // Receber o arquivo Excel do representante
    @PostMapping("/uploadExcel")
    public String receberExcel(@RequestParam("file") MultipartFile file, Model model) {
        try {
            cotacaoService.processarExcel(file);
            model.addAttribute("sucesso", "Arquivo Excel enviado com sucesso!");
        } catch (IOException e) {
            model.addAttribute("erro", "Erro ao processar o arquivo Excel.");
        }
        return "redirect:/cotacao/criar";
    }
}