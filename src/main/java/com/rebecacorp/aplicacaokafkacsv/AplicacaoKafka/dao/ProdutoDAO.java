package com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.dao;

import org.springframework.data.repository.CrudRepository;

import com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.model.Produto;

public interface ProdutoDAO extends CrudRepository<Produto, Integer>{
    
}
