package com.cotacao.service;

import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cotacao.enums.Status;
import com.cotacao.model.Cotacao;
import com.cotacao.model.CotacaoProduto;
import com.cotacao.model.Produto;
import com.cotacao.repositories.CotacaoProdutoRepository;
import com.cotacao.repositories.CotacaoRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;
    private final CotacaoProdutoRepository cotacaoProdutoRepository;
    private final CotacaoProdutoService cotacaoProdutoService;
    private final ProdutoService produtoService;

    public CotacaoService(CotacaoRepository cotacaoRepository, CotacaoProdutoRepository cotacaoProdutoRepository, CotacaoProdutoService cotacaoProdutoService, ProdutoService produtoService) {
        this.cotacaoRepository = cotacaoRepository;
        this.cotacaoProdutoRepository = cotacaoProdutoRepository;
        this.cotacaoProdutoService = cotacaoProdutoService;
        this.produtoService = produtoService;
    }

    public Cotacao buscarCotacaoPorId(Long id) {
        Optional<Cotacao> cotacao = cotacaoRepository.findById(id);
        return cotacao.orElse(null);  // Retorna a cotação encontrada ou null se não encontrada
    }
    
    public Cotacao criarCotacao(String nomeCotacao, List<String> nomesProdutos) {
        // Criar uma nova cotação
        Cotacao novaCotacao = new Cotacao();
        novaCotacao.setNome(nomeCotacao);

        // Salvar a cotação no banco de dados
        Cotacao cotacaoSalva = cotacaoRepository.save(novaCotacao);

        // Associar os produtos à cotação
        for (String nomeProduto : nomesProdutos) {
            // Buscar ou criar o produto usando o serviço existente
            Produto produto = produtoService.saveOrFindProduto(nomeProduto.trim());

            // Criar o vínculo entre cotação e produto
            CotacaoProduto cotacaoProduto = new CotacaoProduto();
            cotacaoProduto.setProduto(produto);
            cotacaoProduto.setCotacao(cotacaoSalva);

            // Salvar o vínculo no banco
            cotacaoProdutoService.salvar(cotacaoProduto); //dando erro
        }

        return cotacaoSalva;
    }

    public List<Cotacao> listarCotasAtivas() {
        return cotacaoRepository.findByStatus(Status.ATIVO);
    }

    public List<CotacaoProduto> listarPorRepresentante(Long representanteId) {
        return cotacaoProdutoRepository.findByRepresentanteId(representanteId);
    }

    
    public void consolidarCotacao(Long cotacaoId) {
        Optional<Cotacao> cotacao = cotacaoRepository.findById(cotacaoId);
        if (cotacao.isPresent()) {
            Cotacao c = cotacao.get();
            c.setStatus(Status.CONSOLIDADO);
            cotacaoRepository.save(c);
        }
    }
    
    public void processarExcel(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // Pega a primeira aba do Excel

            // Percorrer as linhas da planilha, começando da linha 1 (índice 0 é o cabeçalho)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i); // Pega a linha i

                if (row != null) {
                    // Ler os dados das células
                    String nomeProduto = row.getCell(0).getStringCellValue();  // Nome do produto
                    Double preco = row.getCell(1).getNumericCellValue();       // Preço do produto

                    // Aqui você pode fazer algo com os dados, como salvar os preços no banco
                    System.out.println("Produto: " + nomeProduto + ", Preço: " + preco);
                }
            }
        } catch (IOException e) {
            throw new IOException("Erro ao processar o arquivo Excel.", e);
        }
    }



}