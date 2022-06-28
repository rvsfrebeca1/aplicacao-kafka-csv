package com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.service;

import org.springframework.http.ResponseEntity;

import com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.model.Produto;

public interface IProdutoService {
    public ResponseEntity<Object> recuperarTodosOsProdutos();
    public ResponseEntity<Object> recuperarProdutoPeloId(Integer id);
    public ResponseEntity<Object> adicionarProduto(Produto novo);
    public ResponseEntity<Object> atualizarProduto(Produto atualizado, Integer id);
    public ResponseEntity<Object> removerProduto(Integer id);
}
