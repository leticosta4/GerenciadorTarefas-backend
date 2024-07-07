package com.nossogrupo.GerenciadorTarefas.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;

import com.nossogrupo.GerenciadorTarefas.model.Mensagem;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
// import com.nossogrupo.GerenciadorTarefas.model.projection.TarefaProjection;
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

    

}
