package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.repository.TarefaRepository;


@RestController
public class TarefaController { 

    @Autowired 
    private TarefaRepository acaoTarefa; //objeto para excucao dos metodos 

    //USAR POST EM TODAS AS ROTAS QUE PRECISEM DE DADOS SEM SER VIA URL

    @GetMapping("/atividades")
    public String atividades() {
        //provavelmente usar o findall do repository da tarefa
        return "Bem-vindo ao Gerenciador de Tarefas!";
    }

    @GetMapping("/tasks/{titulo}") 
    public String task(@PathVariable String titulo) {
        return "mostrando info da task clicada: " + titulo;
    }

    //rotas-metodos especificas das tasks 
    @GetMapping("/criar_task") 
    public Tarefa criarTask(@RequestBody Tarefa novaTarefa) {
        System.out.println("criando uma task");

        //implementar dpeois talvez com funcoes extras

        return acaoTarefa.save(novaTarefa);
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
