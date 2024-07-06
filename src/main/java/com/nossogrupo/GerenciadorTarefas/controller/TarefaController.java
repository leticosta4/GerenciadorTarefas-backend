package com.nossogrupo.GerenciadorTarefas.controller;

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

    ///acho que tem que especificar o user aqui em muitas rotas - questao de padronizacao e seguranca

    @GetMapping("/api/{userId}/atividades")
    public List<TarefaProjection> atividades(@PathVariable Long userId) {
        System.out.println("Bem-vindo ao Gerenciador de Tarefas! user com ID:" + userId);

        List<TarefaProjection> listaTarefasUser = acaoTarefa.findByUserUserId(userId);
        int quantidadeTarefas = listaTarefasUser.size();
        System.out.println("o user com ID: " + userId + " possui " + quantidadeTarefas + " TAREFAS");
        return listaTarefasUser;
    }


    @GetMapping("/api/{userId}/atividades/filtro_status_{status}") //ou ?ordem=dataCriacao&direcao=asc
    public List<TarefaProjection> filtrarTasksStatus(@PathVariable Long userId, @PathVariable String status, @RequestParam(required = false) String ordem, @RequestParam(required = false, defaultValue = "asc") String direcao) {
        
        List<TarefaProjection> listaFiltradaStatus;

        if (ordem != null) {
            if (ordem.equalsIgnoreCase("dataCriacao")) {
                if (direcao.equalsIgnoreCase("asc")) {
                    listaFiltradaStatus = acaoTarefa.findByUserUserIdAndStatusOrderByDataCriacao(userId, status);
                } else {
                    listaFiltradaStatus = acaoTarefa.findByUserUserIdAndStatusOrderByDataCriacaoDesc(userId, status);
                }
                System.out.println("filtrando as tasks pelo status e ordenando por data de criacao " + direcao);
            }
            
            else if (ordem.equalsIgnoreCase("dataFinal")) {
                if (direcao.equalsIgnoreCase("asc")) {
                    listaFiltradaStatus = acaoTarefa.findByUserUserIdAndStatusOrderByDataFinal(userId, status);
                } else {
                    listaFiltradaStatus = acaoTarefa.findByUserUserIdAndStatusOrderByDataFinalDesc(userId, status);
                }
                System.out.println("filtrando as tasks pelo status e ordenando por data data final " + direcao);
            }
            
            else {
                listaFiltradaStatus = acaoTarefa.findByUserUserIdAndStatus(userId, status);
                System.out.println("filtrando tasks pelo status somente");
            }
        } else {
            listaFiltradaStatus = acaoTarefa.findByUserUserIdAndStatus(userId, status);
            System.out.println("filtrando tasks pelo status somente");
        }

        return listaFiltradaStatus;
    }

    @PostMapping("/api/{userId}/atividades/criar_task") 
    @Transactional
    public Tarefa criarTask(@PathVariable Long userId, @RequestBody Tarefa novaTarefa) {
        System.out.println("criando uma task");
        System.out.println("JSON recebido: " + novaTarefa.toString());
        
        TaskUser user = novaTarefa.getUser();
        if (user != null) {
            //Long userId = user.getUserId(); //antes de passar o userId na URL
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

    @GetMapping("/api/{userId}/atividades/{tarefaId}") //acho que tem que especificar o user antes do id da tarefa
    public TarefaProjection task(@PathVariable Long userId, @PathVariable Long tarefaId) {
        System.out.println("mostrando info da task clicada com id: " + tarefaId);
        
        return acaoTarefa.findByTarefaId(tarefaId);
    }

    @PutMapping("/api/{userId}/atividades/{tarefaId}/editar_task") //acho que tem que passar o id especifico da task talvez
    @Transactional
    public Tarefa editarTask(@PathVariable Long userId, @PathVariable Long tarefaId, @RequestBody Tarefa tarefa) {
        System.out.println("editando uma task:\nID:" + tarefa.getTarefaId() + "\nTITLE: " + tarefa.getTitulo());
        return acaoTarefa.save(tarefa);
    }

    @DeleteMapping("/api/{userId}/atividades/{tarefaId}/remover_task") 
    @Transactional
    public void removerTask(@PathVariable Long userId, @PathVariable Long tarefaId) {
        System.out.println("removendo a task com ID: "+ tarefaId);
        acaoTarefa.deleteByTarefaId(tarefaId);
    }
}
