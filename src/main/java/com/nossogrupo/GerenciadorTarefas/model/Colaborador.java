package com.nossogrupo.GerenciadorTarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "colaborador", uniqueConstraints = {@UniqueConstraint(columnNames = "colaboradorId"), @UniqueConstraint(columnNames = "github"), @UniqueConstraint(columnNames = "linkedin")})
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colaboradorId; 
    private String nome;
    private String funcao;
    private String github;
    private String linkedin;
    private String cor_primaria;
    private String cor_secundaria;

    public Colaborador(){}

    public Colaborador(Long colaboradorId, String nome, String funcao, String github, String linkedin, String cor_primaria, String cor_secundaria){
        this.colaboradorId = colaboradorId;
        this.nome = nome;
        this.funcao = funcao;
        this.github = github;
        this.linkedin = linkedin;
        this.cor_primaria = cor_primaria;
        this.cor_secundaria = cor_secundaria;
    }

    public Long getColaboradorId() {
        return colaboradorId;
    }
    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getFuncao() {
        return funcao;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public String getGithub() {
        return github;
    }
    public void setGithub(String github) {
        this.github = github;
    }
    public String getLinkedin() {
        return linkedin;
    }
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    public String getCor_primaria() {
        return cor_primaria;
    }
    public void setCor_primaria(String cor_primaria) {
        this.cor_primaria = cor_primaria;
    }
    public String getCor_secundaria() {
        return cor_secundaria;
    }
    public void setCor_secundaria(String cor_secundaria) {
        this.cor_secundaria = cor_secundaria;
    }
}
