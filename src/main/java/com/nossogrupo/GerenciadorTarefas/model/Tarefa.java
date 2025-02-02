package com.nossogrupo.GerenciadorTarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDate;


@Entity
@Table(name = "tarefa", uniqueConstraints = {@UniqueConstraint(columnNames = "tarefaId")})
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

    @ManyToOne
    @JoinColumn(name = "userId")
    private TaskUser user; //chave estrangeira

    public Tarefa() {} 

    public Tarefa(Long tarefaId, String titulo, String descricao, String status, String dataCriacao, String dataFinal, String corFundo, String local, TaskUser user){
        this.tarefaId = tarefaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataCriacao = (dataCriacao != null) ? LocalDate.parse(dataCriacao) : null;
        this.dataFinal = (dataFinal != null) ? LocalDate.parse(dataFinal) : null;
        this.corFundo = corFundo;
        this.local = local;
        this.user = user;
        setandoValoresPadrao();
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
        this.status = status;
    }

    public LocalDate getDataCriacao(){
        return this.dataCriacao;
    }

    public void setDataCriacao(String dataCriacao){
        this.dataCriacao =  LocalDate.parse(dataCriacao);
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

    public void setCorFundo(String corFundo){
        this.corFundo = corFundo;
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

    public void setandoValoresPadrao(){
        if (this.descricao == null){
            this.descricao = "Descrição para " + this.titulo;
        }
        if (this.status == null){
            this.status = "Pendente";
        }
        if (this.dataCriacao == null){
            this.dataCriacao = LocalDate.now();;
        }
        if(LocalDate.now().isAfter(this.getDataFinal())){
            this.setStatus("Atrasada");
        }
        if (this.corFundo == null){
            this.corFundo = "#81ACF0";
        }
        if (this.local == null){
            this.local = "Nenhum local definido pelo usuário";
        }
    }
}
