package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.model.projection.UserProjection;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;
import com.nossogrupo.GerenciadorTarefas.service.UserService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*")
public class TaskUserController {

    @Autowired TaskUserRepository acaoUser;

    @Autowired UserService servicoUser;

    @PostMapping("/GerenciadorTarefas/cadastro")
    @Transactional
    public TaskUser cadastrarNovoUser(@RequestBody TaskUser novoUser) {
        System.out.println("oi esse é o signINsignUP - usuarios nao logados");
        //mandar o json do user logado p o front
        return acaoUser.save(novoUser);
    }

    @PostMapping("/GerenciadorTarefas/login")  //dps mudar p PostMapping, acho q n, conferir depois
    public ResponseEntity<?> login(@RequestBody TaskUser usuario) {
        return servicoUser.login(usuario); //MUDAR DEPOIS PARA O USER PROJECTION =====> id e nome
    }

    @GetMapping("/GerenciadorTarefas/{userId}/conta") 
    public UserProjection contaUser(@PathVariable Long userId) {
        System.out.println("Bem-vindo a sua conta do user com ID:" + userId + "mostrando a info do user clicado");
        return acaoUser.findByUserId(userId); 
    }

    @PutMapping("/GerenciadorTarefas/{userId}/editar_conta") 
    @Transactional
    public TaskUser editarContaUser(@RequestBody TaskUser user) { 
        System.out.println("o user edita dados da sua conta. ID: " + user.getUserId() + " - nome: " + user.getNome());
        return acaoUser.save(user);
    }

    @DeleteMapping("/GerenciadorTarefas/{userId}/apagar_conta") 
    @Transactional
    public void removerContaUser(@PathVariable Long userId) {
        System.out.println("removendo o user com ID: "+ userId);
        acaoUser.removeByUserId(userId);
    }
}
