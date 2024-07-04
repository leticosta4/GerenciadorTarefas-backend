package com.nossogrupo.GerenciadorTarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.cglib.core.Local;

@Entity
public class TaskUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String nome;
    private String email;
    private String senha;
    private ArrayList <Tarefa> listaUserTasks; //ver como tratar isso melhor depois 

    public TaskUser(Long id, String nome, String email, String senha){
        this.id = id; ///nao sei se devemos tratar aqui ou n essa atribuição do id
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        //ver como tratar o arraylist
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
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

    public ArrayList<Tarefa> getListaUserTasks() {
        return listaUserTasks;
    }

    // Setter para o ArrayList
    public void setListaUserTasks(ArrayList<Tarefa> listaUserTasks) {
        this.listaUserTasks = listaUserTasks;
    }
}
