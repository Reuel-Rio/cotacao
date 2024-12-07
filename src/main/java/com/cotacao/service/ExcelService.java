package com.cotacao.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.cotacao.model.Cotacao;
import com.cotacao.model.CotacaoProduto;
import com.cotacao.model.Representante;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public ByteArrayInputStream criarExcelCotacao(List<CotacaoProduto> produtos, Representante representante, String nomeDaCotacao) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Cotação");

        // Header
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nome do Produto");
        header.createCell(1).setCellValue("Preço");
        header.createCell(2).setCellValue("Pedido Mínimo");
        header.createCell(3).setCellValue("Representante");
        header.createCell(4).setCellValue("Cotação");

        // Dados
        int rowNum = 1;
        for (CotacaoProduto produto : produtos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(produto.getProduto().getNome());
            row.createCell(1).setCellValue("");
        }

        Row pedidoMinimo = sheet.createRow(rowNum);
        pedidoMinimo.createCell(2).setCellValue(representante.getPedidoMinimo());
        Row representanteNome = sheet.createRow(rowNum + 1);
        representanteNome.createCell(3).setCellValue(representante.getNome());
        Row cotacaoNome = sheet.createRow(rowNum + 2);
        cotacaoNome.createCell(4).setCellValue(nomeDaCotacao);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    public void gerarExcel(List<Cotacao> cotacoes, String nomeCotacao, String nomeRepresentante, Double pedidoMinimo, String caminhoArquivo) throws IOException {
        // Criar um Workbook
        Workbook workbook = new XSSFWorkbook();

        // Criar a aba para a cotação
        Sheet sheet = workbook.createSheet("Cotação");

        // Criar a primeira linha com título
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nome do Produto");
        headerRow.createCell(1).setCellValue("Preço");

        // Preencher as linhas com as cotações
        int rowNum = 1;
        for (Cotacao cotacao : cotacoes) {
            for (CotacaoProduto cotacaoProduto : cotacao.getCotacaoProdutos()) {  // Acessa os produtos associados à cotação
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(cotacaoProduto.getProduto().getNome());  // Nome do produto
                row.createCell(1).setCellValue(""); // A coluna de preço estará vazia para o representante preencher
            }
        }

        // Adicionar uma linha para Pedido Mínimo
        Row pedidoMinimoRow = sheet.createRow(rowNum++);
        pedidoMinimoRow.createCell(0).setCellValue("Pedido Mínimo");
        pedidoMinimoRow.createCell(1).setCellValue(pedidoMinimo);

        // Adicionar nome do Representante
        Row representanteRow = sheet.createRow(rowNum++);
        representanteRow.createCell(0).setCellValue("Nome do Representante");
        representanteRow.createCell(1).setCellValue(nomeRepresentante);

        // Adicionar nome da Cotação
        Row nomeCotacaoRow = sheet.createRow(rowNum++);
        nomeCotacaoRow.createCell(0).setCellValue("Nome da Cotação");
        nomeCotacaoRow.createCell(1).setCellValue(nomeCotacao);

        // Salvar o arquivo Excel
        try (FileOutputStream fileOut = new FileOutputStream(caminhoArquivo)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new IOException("Erro ao gerar arquivo Excel", e);
        } finally {
            workbook.close();
        }
    }
}