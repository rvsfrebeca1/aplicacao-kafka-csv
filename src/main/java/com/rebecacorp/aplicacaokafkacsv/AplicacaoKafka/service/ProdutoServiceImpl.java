package com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.service;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.bcel.AtAjAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.dao.ProdutoDAO;
import com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.model.Produto;

@Component
public class ProdutoServiceImpl implements IProdutoService{

    @Autowired
    private ProdutoDAO dao;

    @Override
    public ResponseEntity<Object> recuperarTodosOsProdutos() {
        
        List<Produto> produtos = new ArrayList<Produto> ();

        try {

            for (Produto p: dao.findAll()){
                produtos.add(p);
            }

            if(produtos.isEmpty()){
                return ResponseEntity.status(404).body("{\"message\":\"Não existem usuarios a serem mostrados\"}");
            }

            return ResponseEntity.status(201).body(produtos);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("{\"message\":\"Usuário não encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<Object> recuperarProdutoPeloId(Integer id) {
        var produtoEncontrado = dao.findById(id);

        if(produtoEncontrado.isEmpty()){
            return ResponseEntity.status(404).body("{\"message\":\"Não existe um usuario com esse id no nosso banco\"}");
        }
        try {
            return ResponseEntity.status(201).body(produtoEncontrado);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
        
    }

    @Override
    public ResponseEntity<Object> adicionarProduto(Produto novo) {
        if(novo.getNome() == null){
            return ResponseEntity.status(400).body("{\"message\":\"Campo 'nome' precisa ser informado.\"}");
        }

        if(novo.getEstoque() == null){
            return ResponseEntity.status(400).body("{\"message\":\"Campo 'estoque' precisa ser informado.\"}");
        }

        try {
            dao.save(novo);
            return ResponseEntity.status(201).body(novo);

        } catch (Exception e) {
            e.printStackTrace();
            
            return ResponseEntity.status(500).body("{\"message\":\"Não foi possivel adicionar o produtp.\"}");
        }
    
    
    }

    @Override
    public ResponseEntity<Object> removerProduto(Integer id) {
        var produtoEncontrado = dao.findById(id);

        if(produtoEncontrado.isEmpty()){
            return ResponseEntity.status(404).body("{\"message\":\"Usuario não encontrado.\"}");
        }

        try {
            dao.deleteById(id);
            return ResponseEntity.ok("Produto deletado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Object> atualizarProduto(Produto atualizado, Integer id) {
        var produtoEncontrado = dao.findById(id);

        if(produtoEncontrado.isEmpty()){
            return ResponseEntity.status(404).body("{\"message\":\"Usuario não encontrado.\"}");
        }

        if(atualizado.getNome() == null){
            return ResponseEntity.status(400).body("{\"message\":\"Campo 'nome' precisa ser informado.\"}");
        }

        if(atualizado.getEstoque() == null){
            return ResponseEntity.status(400).body("{\"message\":\"Campo 'estoque' precisa ser informado.\"}");
        }

        try {
            Produto novoProduto = new Produto(id, atualizado.getNome(), atualizado.getEstoque());
            dao.save(novoProduto);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
