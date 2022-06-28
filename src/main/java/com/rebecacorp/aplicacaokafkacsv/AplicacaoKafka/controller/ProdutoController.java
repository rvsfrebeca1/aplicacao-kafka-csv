package com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVWriter;
import com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.model.Produto;
import com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.service.IProdutoService;
import com.rebecacorp.aplicacaokafkacsv.util.S3Util;
import com.rebecacorp.aplicacaokafkacsv.util.WriterGenerator;


@RestController
public class ProdutoController {
    


    @Autowired
    private IProdutoService service;

    @GetMapping("/")
    public ResponseEntity<String> hello() throws IOException{

        return ResponseEntity.ok("Welcome to API");
    }

    @GetMapping("/produtos")
    public ResponseEntity<?> recuperarTodos(){
        return ResponseEntity.ok(service.recuperarTodosOsProdutos());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<?> recuperarProdutoPeloId(@PathVariable Integer id){
        return ResponseEntity.ok(service.recuperarProdutoPeloId(id));
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> adicionarProduto(@RequestBody Produto novo) throws Exception{
        CSVWriter csvWriter = WriterGenerator.generatewriter();

        String[] gravacao = {
            String.format("id: %d", novo.getId()),
            String.format("nome: %s", novo.getNome()),
            String.format("estoque: %d", novo.getEstoque())
        };
        InputStream file = new FileInputStream("/Users/rebeca.ferreira/Desktop/repos-github-pessoal/aplicacao-kafka-csv/pessoas.csv");
        csvWriter.writeNext(gravacao);
        csvWriter.close();

        S3Util.uploadFile("pessoas.csv",file);
       
        return service.adicionarProduto(novo);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<?> atualizarProduto(@RequestBody Produto atualizado, @PathVariable Integer id){
        return service.atualizarProduto(atualizado, id);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Integer id){
        return service.removerProduto(id);
    }
}
