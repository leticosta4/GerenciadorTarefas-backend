package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.model.projection.UserProjection;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

import jakarta.transaction.Transactional;

@RestController
public class TaskUserController {

    @Autowired TaskUserRepository userAcao;

    @PostMapping("/api/cadastro")
    @Transactional
    public TaskUser cadastrarNovoUser(@RequestBody TaskUser novoUser) {
        System.out.println("oi esse é o signINsignUP - usuarios nao logados");
        //mandar o json do user logado p o front
        return userAcao.save(novoUser);
    }

    @GetMapping("/api/login")  //dps mudar p PostMapping
    public String login() {
        //mandar o json do user logado p o front
        return "oi esse é o signINsignUP - usuarios logados";
    }

    @GetMapping("/api/{userId}/conta") 
    public UserProjection contaUser(@PathVariable Long userId) {
        System.out.println("Bem-vindo a sua conta do user com ID:" + userId + "mostrando a info do user clicado");
        return userAcao.findByUserId(userId); 
    }

    @PutMapping("/api/{userId}/editar_conta") 
    @Transactional
    public TaskUser editarContaUser(@RequestBody TaskUser user) { 
        System.out.println("o user edita dados da sua conta. ID: " + user.getUserId() + " - nome: " + user.getNome());
        return userAcao.save(user);
    }

    @DeleteMapping("/api/{userId}/apagar_conta") 
    @Transactional
    public void removerContaUser(@PathVariable Long userId) {
        System.out.println("removendo o user com ID: "+ userId);
        userAcao.removeByUserId(userId);
    }
}
