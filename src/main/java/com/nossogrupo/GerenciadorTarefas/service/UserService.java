package com.nossogrupo.GerenciadorTarefas.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nossogrupo.GerenciadorTarefas.model.Mensagem;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

@Service
public class UserService {
    
    @Autowired private TaskUserRepository acaoUser; 
    @Autowired private Mensagem mensagem;

    public ResponseEntity<?> cadastroNovoUser(TaskUser novoUser){ //depois rever o json que e enviado - E TEM QUE TRATAR A QUESTAO DE EMAIL REPETIDO
        if (novoUser.getNome().equals("") || novoUser.getEmail().equals("") || novoUser.getSenha().equals("")){
            mensagem.setMensagem("O nome não pode ser vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (acaoUser.existsByEmail(novoUser.getEmail())){
            mensagem.setMensagem("O email tem que ser unico");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acaoUser.save(novoUser), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> login(TaskUser usuario){ 
        ArrayList <TaskUser> listaUsers = acaoUser.findAllBy();
        for(TaskUser user : listaUsers){
            if(user.getEmail().equals(usuario.getEmail()) && user.getSenha().equals(usuario.getSenha())){ 
                user.checaPrazoTarefas(); 
                acaoUser.save(user); 
                mensagem.setMensagem("user encontrado");

                TaskUser userJsonSimples = new TaskUser(user.getUserId(), user.getNome(), user.getEmail(), user.getSenha());

                return new ResponseEntity<>(userJsonSimples, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> contaUser(String stringUserId){
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
                return new ResponseEntity<>(acaoUser.findByUserId(userId), HttpStatus.OK);
            } 
        }
        mensagem.setMensagem("Não foi encontrada nenhuma pessoa com o ID fornecido");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> editarContaUser(String stringUserId, TaskUser usuario){ // - ta meio bugado
        Long userId;
        Boolean encontrouUserNaLista = false;
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
                encontrouUserNaLista = true;
                break;
            } 
        } 

        if(!encontrouUserNaLista){
            mensagem.setMensagem("Não foi encontrada nenhuma pessoa com o ID fornecido");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } 

        if (usuario.getNome().equals("") || usuario.getEmail().equals("") || usuario.getSenha().equals("")){
            mensagem.setMensagem("Não são permitido campos vazios");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acaoUser.save(usuario), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> removerContaUser(String stringUserId){ 
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
                acaoUser.removeByUserId(userId);
                return new ResponseEntity<>(HttpStatus.OK);
            } 
        }
        mensagem.setMensagem("Não foi encontrada nenhuma pessoa com o ID fornecido");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
}