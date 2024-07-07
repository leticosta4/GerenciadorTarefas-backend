package com.nossogrupo.GerenciadorTarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.CascadeType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task_user", uniqueConstraints = {@UniqueConstraint(columnNames = "userId"), @UniqueConstraint(columnNames = "email")})
public class TaskUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; 
    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> listaTarefasUser = new ArrayList<>();

    public TaskUser() {} //construtor padrao jpa
    public TaskUser(Long userId, String nome) {}; //para facilitar o envio no front - rastreamento pleo id
    public TaskUser(Long userId, String nome, String email, String senha){
        this.userId = userId; 
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.listaTarefasUser = new ArrayList<>();
    }

    public Long getUserId(){
        return this.userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }
    
    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public List<Tarefa> getListaTarefasUser() {
        return listaTarefasUser;
    }

    public void setListaTarefasUser(List<Tarefa> listaTarefasUser) {
        this.listaTarefasUser = listaTarefasUser;
    }

    public void checaPrazoTarefas(){
        List<Tarefa> listaTarefasUser = this.getListaTarefasUser();
        for(Tarefa tarefaUser : listaTarefasUser){
            if(LocalDate.now().isAfter(tarefaUser.getDataFinal())){
                tarefaUser.setStatus("Atrasada");
                break;
            }
        }
    }
}
