package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nossogrupo.GerenciadorTarefas.model.Mensagem;
import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.service.TarefaService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*")
public class TarefaController { 
    @Autowired private TarefaService servicoTarefa;
    

    @GetMapping("/GerenciadorTarefas/{userId}/atividades")
     public ResponseEntity<?> atividades(@PathVariable String userId) {
        return servicoTarefa.atividades(userId);
    }   


    @GetMapping({"/GerenciadorTarefas/{userId}/atividades/filtro_status_{status}",
                "/GerenciadorTarefas/{userId}/atividades/filtro_status_{status}?ordem={ordem}&direcao=asc",
                "/GerenciadorTarefas/{userId}/atividades/filtro_status_{status}?ordem={ordem}&direcao=desc",}) 
    public ResponseEntity<?> filtrarTasksStatus(@PathVariable String userId, @PathVariable String status, @RequestParam(required = false) String ordem, @RequestParam(required = false, defaultValue = "asc") String direcao) {
        return servicoTarefa.filtrarTaskStatus(userId, status, ordem, direcao);
    }    


    @PostMapping("/GerenciadorTarefas/{userId}/atividades/criar_task") 
    @Transactional
    public ResponseEntity<?> criarTask(@PathVariable String userId, @RequestBody Tarefa novaTarefa){
        return servicoTarefa.criarTask(userId, novaTarefa);
    }

    @GetMapping("/GerenciadorTarefas/{userId}/atividades/{tarefaId}") 
    public ResponseEntity<?> task(@PathVariable String userId, @PathVariable String tarefaId) {
        return servicoTarefa.task(userId, tarefaId);
    }

    @PutMapping("/GerenciadorTarefas/{userId}/atividades/{tarefaId}/{action}") 
    @Transactional
    public ResponseEntity<?> editarTask(@PathVariable String userId, @PathVariable String tarefaId, @PathVariable String action, @RequestBody Tarefa tarefa) {
        int flagConcluida;
        if ("editar_task".equalsIgnoreCase(action) || "marcar_concluida".equalsIgnoreCase(action)) {
            if("editar_task".equalsIgnoreCase(action)){ flagConcluida = 0; } else{ flagConcluida = 1; }
            return servicoTarefa.editarTask(userId, tarefaId, tarefa, flagConcluida);
        } else {
            Mensagem mensagem = new Mensagem();
            mensagem.setMensagem("Ação desconhecida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/GerenciadorTarefas/{userId}/atividades/{tarefaId}/remover_task") //talvez seja afetado se tiver seleçao
    @Transactional
    public ResponseEntity<?> removerTask(@PathVariable String userId, @PathVariable String tarefaId) {
        return servicoTarefa.removerTask(userId, tarefaId);
    }
}
