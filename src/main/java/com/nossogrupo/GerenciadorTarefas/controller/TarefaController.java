package com.nossogrupo.GerenciadorTarefas.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.repository.TarefaRepository;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;
import com.nossogrupo.GerenciadorTarefas.model.TarefaProjection;


@RestController
public class TarefaController { 

    @Autowired 
    private TarefaRepository acaoTarefa; //objeto para excucao dos metodos 
    @Autowired
    private TaskUserRepository acaoUser; //teste

    //USAR POST EM TODAS AS ROTAS QUE PRECISEM DE DADOS SEM SER VIA URL

    @GetMapping("/atividades")
    public ArrayList<TarefaProjection> atividades() {
        System.out.println("Bem-vindo ao Gerenciador de Tarefas!");

        return acaoTarefa.findAllBy();
    }

    @GetMapping("/tasks/{titulo}") 
    public String task(@PathVariable String titulo) {
        return "mostrando info da task clicada: " + titulo;
    }

    //rotas-metodos especificas das tasks 
    @PostMapping("/criar_task") 
    public Tarefa criarTask(@RequestBody Tarefa novaTarefa) {
        System.out.println("criando uma task");
        System.out.println("JSON recebido: " + novaTarefa.toString());
        
        TaskUser user = novaTarefa.getUser();
        if (user != null) {
            Long userId = user.getUserId();
            if (userId != null) {
                System.out.println("ID do usuário recebido: " + userId);
                TaskUser usuarioDaVez = acaoUser.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));
                novaTarefa.setUser(usuarioDaVez);
            } else {
                throw new RuntimeException("ID do usuário não pode ser nulo.");
            }
        } else {
            throw new RuntimeException("Usuário não pode ser nulo.");
        }

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
