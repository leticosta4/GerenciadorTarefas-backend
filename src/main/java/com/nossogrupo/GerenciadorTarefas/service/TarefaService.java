package com.nossogrupo.GerenciadorTarefas.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nossogrupo.GerenciadorTarefas.model.Mensagem;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.repository.TarefaRepository;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

@Service
public class TarefaService {
    @Autowired private TarefaRepository acaoTarefa; 
    @Autowired private TaskUserRepository acaoUser; 
    @Autowired private Mensagem mensagem;

    public Long validarStringUserId(String stringUserId){
        try {
            return Long.parseLong(stringUserId);
        }
        
        catch (NumberFormatException e) {
            return null;
        }
    }

    public ResponseEntity<?> validarFindUserId(Long userId) {
        ArrayList <TaskUser> listaUsers = acaoUser.findAllBy();
        
        for(TaskUser user : listaUsers){
            if(user.getUserId() == userId){
                return new ResponseEntity<>(HttpStatus.OK);
            } 
        }
        mensagem.setMensagem("Não foi encontrada nenhuma pessoa com o ID fornecido");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
}
