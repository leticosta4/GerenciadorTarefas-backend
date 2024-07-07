package com.nossogrupo.GerenciadorTarefas.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nossogrupo.GerenciadorTarefas.model.Mensagem;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
// import com.nossogrupo.GerenciadorTarefas.repository.TarefaRepository;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

@Service
public class UserService {
    
    // @Autowired private TarefaRepository acaoTarefa; 
    @Autowired private TaskUserRepository acaoUser; 
    @Autowired private Mensagem mensagem;

    //eu achava melhor aqui aumentar a validaçao dos dados - PARA CADASTRO TB

    //MUDAR O RETORNO
    //pegar o user e concatenar em uma lista ou usar o construtor e mandar
    public ResponseEntity<?> login(TaskUser usuario){ 
        ArrayList <TaskUser> listaUsers = acaoUser.findAllBy();
        for(TaskUser user : listaUsers){
            if(user.getEmail().equals(usuario.getEmail()) && user.getSenha().equals(usuario.getSenha())){ //incrementar com algumas msgs
                user.checaPrazoTarefas(); //vendo se tem tarefas atrasadas
                acaoUser.save(user); //salvado no banco
                mensagem.setMensagem("user encontrado");
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
}