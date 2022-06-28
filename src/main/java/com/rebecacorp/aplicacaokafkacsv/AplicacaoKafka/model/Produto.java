package com.rebecacorp.aplicacaokafkacsv.AplicacaoKafka.model;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "estoque")
    private Integer estoque;

    public Produto(){

    };

    public Produto(Integer id, String nome, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}
