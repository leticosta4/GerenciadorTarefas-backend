package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
// import com.nossogrupo.GerenciadorTarefas.repository.TarefaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TarefaController { 

    //talvez precise rotas duplicadas sem a query tratada

    @GetMapping("/tasks/{titulo}") 
    public String task(@PathVariable String titulo) {
        return "mostrando info da task clicada: " + titulo;
    }

    //rotas-metodos especificas das tasks 
    @GetMapping("/criar_task/{titulo}") 
    public String criarTask(@PathVariable String titulo) {
        return "criando uma task: " + titulo;
    }

    @GetMapping("/remover_task/{titulo}") 
    public String removerTask(@PathVariable String titulo) {
        return "removendo uma task: " + titulo;
    }

    @GetMapping("/editar_task/{titulo}") 
    public String editarTask(@PathVariable String titulo) {
        return "editando uma task: " + titulo;
    }

    @GetMapping("/filtrando_tasks") 
    public String filtrarTrasks() {
        return "filtrando as tasks";
    }

    //alguns testes de rota com atributo especifico da task
    @PostMapping("/tarefa")
    public Tarefa task(@RequestBody Tarefa t){
        return t;
    }
}
