package com.nossogrupo.GerenciadorTarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;

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
    private ArrayList <Tarefa> listaTarefasUser; 

    public TaskUser() {} //construtor padrao jpa

    public TaskUser(Long user_id, String nome, String email, String senha){
        this.user_id = user_id; ///nao sei se devemos tratar aqui ou n essa atribuição do id
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

    public ArrayList<Tarefa> getListaTarefasUser() {
        return listaTarefasUser;
    }

    // Setter para o ArrayList
    public void setListaTarefasUser(ArrayList<Tarefa> listaTarefasUser) {
        this.listaTarefasUser = listaTarefasUser;
    }
}
