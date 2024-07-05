package com.nossogrupo.GerenciadorTarefas.model;

//uso de jakarta em vez de javax explicado aqui: https://stackoverflow.com/questions/15598210/the-import-javax-persistence-cannot-be-resolved

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String status;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataCriacao;
    private LocalDate dataFinal;

    private String corFundo; //setar um default caso seja nulo
    private String local;
    //ver como vamos tratar o anexo de imagem depois

    public Tarefa() {} //construtor padrao jpa

    public Tarefa(Long id, String titulo, String descricao, String status, String dataCriacao, String dataFinal, String corFundo, String local){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataCriacao = LocalDate.parse(dataCriacao);
        this.dataFinal = LocalDate.parse(dataFinal);
        this.corFundo = corFundo;
        this.local = local;
    }


    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }
    
    public String getTitulo(){
        return this.titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public LocalDate getDataCriacao(){
        return this.dataCriacao;
    }

    public void setDataCriacao(String dataCriacao){
        if (getDataCriacao() == null){
            this.dataCriacao = LocalDate.now(); //talvez aqui não sei
        } else {
            this.dataCriacao =  LocalDate.parse(dataCriacao);
        }
    }

    public LocalDate getDataFinal(){
        return this.dataFinal;
    }

    public void setDataFinal(String dataFinal){
        this.dataFinal =  LocalDate.parse(dataFinal);
    }

    public String getCorFundo(){
        return this.corFundo;
    }

    public void setCorFundo(String corFundo){ //nao sei se deixamos esse tratamento aqui ou nao
        if(getCorFundo() == null){
            this.corFundo = "#81ACF0"; //um tom de azul claro
        } else {
            this.corFundo = corFundo;
        }
    }

    public String getLocal(){
        return this.local;
    }

    public void setLocal(String local){
        this.local = local;
    }
}
