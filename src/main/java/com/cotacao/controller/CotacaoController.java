package com.cotacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cotacao.model.Cotacao;
import com.cotacao.service.CotacaoService;
import com.cotacao.service.ExcelService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @Autowired
    private ExcelService excelService;

    // Página para o lojista criar a cotação
    @GetMapping("/criar")
    public String exibirFormulario(Model model) {
        model.addAttribute("cotacao", new Cotacao());
        return "criar-cotacao";
    }

    @GetMapping("/cotacoes/{id}")
    public String visualizarCotacao(@PathVariable("id") Long id, Model model) {
        Cotacao cotacao = cotacaoService.buscarCotacaoPorId(id);
        if (cotacao != null) {
            model.addAttribute("cotacao", cotacao);
            return "cotacoes/mostrar";  // Página para visualizar os detalhes da cotação
        } else {
            return "redirect:/cotacoes/listar";  // Redireciona para a listagem de cotações se a cotação não for encontrada
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