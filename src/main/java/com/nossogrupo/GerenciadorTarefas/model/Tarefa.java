package com.nossogrupo.GerenciadorTarefas.model;

//uso de jakarta em vez de javax explicado aqui: https://stackoverflow.com/questions/15598210/the-import-javax-persistence-cannot-be-resolved

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import com.nossogrupo.GerenciadorTarefas.model.projection.TarefaProjection;

@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tarefaId;
    private String titulo;
    private String descricao;
    private String status;
    private LocalDate dataCriacao;
    private LocalDate dataFinal;
    private String corFundo; 
    private String local;
    //ver como vamos tratar o anexo de imagem depois

    @ManyToOne
    @JoinColumn(name = "userId")
    private TaskUser user; //chave estrangeira

    public Tarefa() {} //construtor padrao jpa

    public Tarefa(Long tarefaId, String titulo, String descricao, String status, String dataCriacao, String dataFinal, String corFundo, String local, TaskUser user){
        this.tarefaId = tarefaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataCriacao = LocalDate.parse(dataCriacao);
        this.dataFinal = LocalDate.parse(dataFinal);
        this.corFundo = corFundo;
        this.local = local;
        this.user = user;
    }


    public Long getTarefaId(){
        return this.tarefaId;
    }

    public void setTarefaId(Long tarefaId){
        this.tarefaId = tarefaId;
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
        if(getStatus() == null){
            this.status = "A fazer";
        } else {
            this.status = status;
        }
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

    public TaskUser getUser() {
        return user;
    }

    public void setUser(TaskUser user) {
        this.user = user;
    }
}
