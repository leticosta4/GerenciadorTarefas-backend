package com.nossogrupo.GerenciadorTarefas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;

import com.nossogrupo.GerenciadorTarefas.model.projection.TarefaProjection;

import com.nossogrupo.GerenciadorTarefas.repository.TarefaRepository;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

import jakarta.transaction.Transactional;

@RestController
public class TarefaController { 

    @Autowired private TarefaRepository acaoTarefa; 
    @Autowired private TaskUserRepository acaoUser; 

    //USAR POST EM TODAS AS ROTAS QUE PRECISEM DE DADOS SEM SER VIA URL
    @GetMapping("/{userId}/atividades")
    public List<TarefaProjection> atividades(@PathVariable Long userId) {
        System.out.println("Bem-vindo ao Gerenciador de Tarefas! user com ID:" + userId);
        
        return acaoTarefa.findByUserUserId(userId);
    }

    @GetMapping("/tasks/{tarefaId}") 
    public TarefaProjection task(@PathVariable Long tarefaId) {
        System.out.println("mostrando info da task clicada com id: " + tarefaId);
        
        return acaoTarefa.findByTarefaId(tarefaId);
    }

    //rotas-metodos especificas das tasks 
    @PostMapping("/criar_task") 
    @Transactional
    public Tarefa criarTask(@RequestBody Tarefa novaTarefa) {
        System.out.println("criando uma task");
        System.out.println("JSON recebido: " + novaTarefa.toString());
        
        TaskUser user = novaTarefa.getUser();
        if (user != null) {
            Long userId = user.getUserId();
            if (userId != null) {
                System.out.println("ID do usuário recebido: " + userId);
                TaskUser usuarioDaVez = acaoUser.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));
                novaTarefa.setUser(usuarioDaVez);
            } else {
                throw new RuntimeException("ID do usuário não pode ser nulo.");
            }
        } else {
            throw new RuntimeException("Usuário não pode ser nulo.");
        }
        novaTarefa.setandoValoresPadrao();
        return acaoTarefa.save(novaTarefa);
    }

    @PutMapping("/editar_task") //acho que tem que passar o id especifico da task talvez
    @Transactional
    public Tarefa editarTask(@RequestBody Tarefa tarefa) {
        System.out.println("editando uma task:\nID:" + tarefa.getTarefaId() + "\nTITLE: " + tarefa.getTitulo());
        return acaoTarefa.save(tarefa);
    }

    @DeleteMapping("/remover_task/{tarefaId}") 
    @Transactional
    public void removerTask(@PathVariable Long tarefaId) {
        System.out.println("removendo a task com ID: "+ tarefaId);
        acaoTarefa.deleteByTarefaId(tarefaId);
    }

    @GetMapping("/filtrando_tasks") 
    public String filtrarTrasks() {
        return "filtrando as tasks";
    }
}
