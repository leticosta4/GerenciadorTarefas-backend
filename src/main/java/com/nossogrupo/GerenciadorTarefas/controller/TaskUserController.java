package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

@RestController
public class TaskUserController {

    @Autowired TaskUserRepository userAcao;

    //USAR POST EM TODAS AS ROTAS QUE PRECISEM DE DADOS SEM SER VIA URL

    @PostMapping("/cadastro")
    public TaskUser cadastrarNovoUser(@RequestBody TaskUser novoUser) {
        System.out.println("oi esse é o signINsignUP - usuarios nao logados");
        return userAcao.save(novoUser);
    }

    @GetMapping("/login")  //dps mudar p PostMapping
    public String login() {
        return "oi esse é o signINsignUP - usuarios logados";
    }

    // @GetMapping("/conta_user/{user_id}") 
    // public TaskUser contUser(@PathVariable Long user_id, @PathVariable String nome) {
    //     System.out.println("Bem-vindo a sua conta" + nome + "! ID:" + user_id);
    //     return userAcao.findByUser_id(user_id); 
    // }

    // @GetMapping("/editar_conta_user/{user_id}") 
    // public TaskUser editarContaUser(@PathVariable Long user_id, @PathVariable String nome) { //duvida
    //     System.out.println("o user edita dados da sua conta," + nome + "id: " + user_id);
    //     return userAcao.findByUser_id(user_id);
    // }

    //alguns testes de rota com atributo especifico da task
    @PostMapping("/task_user")
    public TaskUser taskUser(@RequestBody TaskUser u){
        return u;
    }
}
