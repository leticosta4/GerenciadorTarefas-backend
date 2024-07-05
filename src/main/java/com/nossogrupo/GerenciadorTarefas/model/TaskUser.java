package com.nossogrupo.GerenciadorTarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.core.Local;

@Entity
@Table(name = "task_user")
public class TaskUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id; 
    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> listaTarefasUser = new ArrayList<>();

    public TaskUser() {} //construtor padrao jpa

    public TaskUser(Long user_id, String nome, String email, String senha){
        this.user_id = user_id; 
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.listaTarefasUser = new ArrayList<>();
    }

    public Long getUserId(){
        return this.user_id;
    }

    public void setUserId(Long user_id){
        this.user_id = user_id;
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
}
