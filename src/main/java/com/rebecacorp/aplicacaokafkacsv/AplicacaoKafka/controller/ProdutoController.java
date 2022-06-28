package com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.model.Produto;
import com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.service.IProdutoService;

@RestController
public class ProdutoController {
    
    @Autowired
    private IProdutoService service;

    @GetMapping("/")
    public ResponseEntity<String> hello(){
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
    public ResponseEntity<?> adicionarProduto(@RequestBody Produto novo){
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
