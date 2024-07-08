package com.nossogrupo.GerenciadorTarefas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nossogrupo.GerenciadorTarefas.model.Mensagem;
import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.model.projection.TarefaProjection;
import com.nossogrupo.GerenciadorTarefas.repository.TarefaRepository;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

@Service
public class TarefaService {
    @Autowired private TarefaRepository acaoTarefa; 
    @Autowired private TaskUserRepository acaoUser; 
    @Autowired private Mensagem mensagem;

    public ResponseEntity<?> atividades(String stringUserId){
        Long userId;
        try {
            userId =  Long.parseLong(stringUserId);
        }
        catch (NumberFormatException e) {
            mensagem.setMensagem("valor inválido para userId");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        ArrayList <TaskUser> listaUsers = acaoUser.findAllBy();
        
        for(TaskUser user : listaUsers){
            if(user.getUserId() == userId){
                return new ResponseEntity<>(acaoTarefa.findByUserUserId(userId), HttpStatus.OK);
            } 
        }
        mensagem.setMensagem("Não foi encontrada nenhuma pessoa com o ID fornecido");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> filtrarTaskStatus(String stringUserId, String status, String ordem, String direcao) {
        Long userId;
        try {
            userId =  Long.parseLong(stringUserId);
        }
        catch (NumberFormatException e) {
            mensagem.setMensagem("valor inválido para userId");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        ArrayList <TaskUser> listaUsers = acaoUser.findAllBy();
        
        for(TaskUser user : listaUsers){
            if(user.getUserId() == userId){

                if (ordem != null) {
                    if (ordem.equalsIgnoreCase("dataCriacao")) {
                        if (direcao.equalsIgnoreCase("asc")) {
                            return new ResponseEntity<>(acaoTarefa.findByUserUserIdAndStatusOrderByDataCriacao(userId, status), HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(acaoTarefa.findByUserUserIdAndStatusOrderByDataCriacaoDesc(userId, status), HttpStatus.OK);
                        }
                    }
                    
                    else if (ordem.equalsIgnoreCase("dataFinal")) {
                        if (direcao.equalsIgnoreCase("asc")) {
                            return new ResponseEntity<>(acaoTarefa.findByUserUserIdAndStatusOrderByDataFinal(userId, status), HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(acaoTarefa.findByUserUserIdAndStatusOrderByDataFinalDesc(userId, status), HttpStatus.OK);
                        }
                    }
                    
                    else {
                        return new ResponseEntity<>(acaoTarefa.findByUserUserIdAndStatus(userId, status), HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<>(acaoTarefa.findByUserUserIdAndStatus(userId, status), HttpStatus.OK);
                }
            } 
        }
        mensagem.setMensagem("Não foi encontrada nenhuma pessoa com o ID fornecido");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> criarTask(String stringUserId, Tarefa novaTarefa){
        Boolean encontrouUserLista = false;
        Long userId;
        try {
            userId =  Long.parseLong(stringUserId);
        }
        catch (NumberFormatException e) {
            mensagem.setMensagem("valor inválido para userId");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        ArrayList <TaskUser> listaUsers = acaoUser.findAllBy();
        for(TaskUser user : listaUsers){
            if(user.getUserId() == userId){
                encontrouUserLista = true;
                break;
            } 
        }

        if(!encontrouUserLista){
            mensagem.setMensagem("Usuário não encontrado com o ID fornecido");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
    
        TaskUser usuarioDaVez = acaoUser.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));
        novaTarefa.setUser(usuarioDaVez); //esse tratamento garante

        if(novaTarefa.getTitulo() == null){
            mensagem.setMensagem("O nome da tarefa precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (novaTarefa.getDataFinal() == null){
            mensagem.setMensagem("A data final da tarefa precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            novaTarefa.setandoValoresPadrao();
            return new ResponseEntity<>(acaoTarefa.save(novaTarefa), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> task(String stringUserId, String stringTarefaId){
        Long userId, tarefaId;
        try {
            userId =  Long.parseLong(stringUserId);
            tarefaId = Long.parseLong(stringTarefaId);
        }
        catch (NumberFormatException e) {
            mensagem.setMensagem("valor inválido para userId ou para tarefaId");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        if(acaoUser.findByUserId(userId) == null){
            mensagem.setMensagem("Usuário não encontrado com o ID fornecido");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        
        List<TarefaProjection> listaTarefasUser = acaoTarefa.findByUserUserId(userId);
        for(TarefaProjection tarefaUser : listaTarefasUser){
            if(tarefaUser.getTarefaId() == tarefaId){
                return new ResponseEntity<>(tarefaUser, HttpStatus.OK);
            }
        }

        mensagem.setMensagem("A tarefa não foi encontrada na lista de tasks do user");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> editarTask(String stringUserId, String stringTarefaId, Tarefa tarefa, int flagConcluida){ 
        Long userId, tarefaId;
        try {
            userId =  Long.parseLong(stringUserId);
            tarefaId = Long.parseLong(stringTarefaId);
        } catch (NumberFormatException e) {
            mensagem.setMensagem("valor inválido para userId ou para tarefaId");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        if(acaoUser.findByUserId(userId) == null){ 
            mensagem.setMensagem("Usuário não encontrado com o ID fornecido");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND); 
        }

        ArrayList <TaskUser> listaUsers = acaoUser.findAllBy();
        for(TaskUser user : listaUsers){
            if ( user.getUserId() == userId){
                tarefa.setUser(user);
            }
        }
        
        List<TarefaProjection> listaTarefasUser = acaoTarefa.findByUserUserId(userId);
        for(TarefaProjection tarefaUser : listaTarefasUser){
            if(tarefaUser.getTarefaId() == tarefaId){
                if(flagConcluida == 1){ tarefa.setStatus("Concluída");}
                tarefa.setandoValoresPadrao();
                return new ResponseEntity<>(acaoTarefa.save(tarefa), HttpStatus.OK);
            }
        }
        mensagem.setMensagem("A tarefa não foi encontrada na lista de tasks do user");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> removerTask(String stringUserId, String stringTarefaId){
        Long userId, tarefaId;
        try {
            userId =  Long.parseLong(stringUserId);
            tarefaId = Long.parseLong(stringTarefaId);
        }
        catch (NumberFormatException e) {
            mensagem.setMensagem("valor inválido para userId ou para tarefaId");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        if(acaoUser.findByUserId(userId) == null){
            mensagem.setMensagem("Usuário não encontrado com o ID fornecido");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        
        List<TarefaProjection> listaTarefasUser = acaoTarefa.findByUserUserId(userId);
        for(TarefaProjection tarefaUser : listaTarefasUser){
            if(tarefaUser.getTarefaId() == tarefaId){
                acaoTarefa.deleteByTarefaId(tarefaId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        mensagem.setMensagem("A tarefa não foi encontrada na lista de tasks do user");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
}
