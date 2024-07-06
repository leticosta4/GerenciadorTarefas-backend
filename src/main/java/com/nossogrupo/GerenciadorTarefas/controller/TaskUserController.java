package com.nossogrupo.GerenciadorTarefas.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.model.projection.TarefaProjection;
import com.nossogrupo.GerenciadorTarefas.model.projection.UserProjection;
import com.nossogrupo.GerenciadorTarefas.repository.TarefaRepository;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

import jakarta.transaction.Transactional;

@RestController
public class TaskUserController {

    @Autowired TaskUserRepository userAcao;

    @PostMapping("/cadastro")
    @Transactional
    public TaskUser cadastrarNovoUser(@RequestBody TaskUser novoUser) {
        System.out.println("oi esse é o signINsignUP - usuarios nao logados");
        return userAcao.save(novoUser);
    }

    @GetMapping("/login")  //dps mudar p PostMapping
    public String login() {
        return "oi esse é o signINsignUP - usuarios logados";
    }

    @GetMapping("/conta_user/{userId}") 
    public UserProjection contUser(@PathVariable Long userId) {
        System.out.println("Bem-vindo a sua conta do user com ID:" + userId + "mostrando a info da task clicada");
        return userAcao.findByUserId(userId); 
    }

    @PutMapping("/editar_conta_user") //acho que tem que passar o id especifico do user talvez
    @Transactional
    public TaskUser editarContaUser(@RequestBody TaskUser user) { 
        System.out.println("o user edita dados da sua conta. ID: " + user.getUserId() + " - nome: " + user.getNome());
        return userAcao.save(user);
    }

    @DeleteMapping("/apagar_conta/{userId}") 
    @Transactional
    public void removerColaborador(@PathVariable Long userId) {
        System.out.println("removendo o user com ID: "+ userId);
        userAcao.removeByUserId(userId);
    }
}
