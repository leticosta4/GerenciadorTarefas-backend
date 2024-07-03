package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.repository.TarefaRepository;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepository;

    // @GetMapping
    // public ArrayList<Tarefa> listarTarefas() {
    //     return (ArrayList<Tarefa>) tarefaRepository.findAll();
    // }

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @GetMapping("/tarefas")
    public String home() {
        return "Bem-vindo ao Gerenciador de Tarefas!";
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow();
        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        // tarefa.setConcluida(tarefaAtualizada.isConcluida());
        return tarefaRepository.save(tarefa);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }
}
