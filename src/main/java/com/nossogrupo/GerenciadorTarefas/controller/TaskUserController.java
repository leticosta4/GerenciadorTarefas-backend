package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
//import com.nossogrupo.GerenciadorTarefas.model.projection.UserProjection;
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
    public ResponseEntity<?> cadastrarNovoUser(@RequestBody TaskUser novoUser) {
        return servicoUser.cadastroNovoUser(novoUser);
    }

    @PostMapping("/GerenciadorTarefas/login")  
    public ResponseEntity<?> login(@RequestBody TaskUser usuario) {
        return servicoUser.login(usuario); 
    }

    @GetMapping("/GerenciadorTarefas/{userId}/conta") 
    public ResponseEntity<?> contaUser(@PathVariable String userId) {
        return servicoUser.contaUser(userId); 
    }

    @PutMapping("/GerenciadorTarefas/{userId}/editar_conta") 
    @Transactional
    public ResponseEntity<?> editarContaUser(@PathVariable String userId, @RequestBody TaskUser user) { 
        return servicoUser.editarContaUser(userId, user);
    }

    @DeleteMapping("/GerenciadorTarefas/{userId}/apagar_conta") 
    @Transactional
    public ResponseEntity<?> removerContaUser(@PathVariable String userId) {
        return servicoUser.removerContaUser(userId);
    }
}
