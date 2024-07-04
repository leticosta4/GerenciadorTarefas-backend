package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
// import com.nossogrupo.GerenciadorTarefas.repository.UserRepository;

@RestController
public class TaskUserController {
    @GetMapping("/cadastro")
    public String cadastro() {
        return "oi esse é o signINsignUP";
    }

    @GetMapping("/login")
    public String login() {
        return "oi esse é o signINsignUP";
    }

    @GetMapping("/atividades")
    public String atividades() {
        return "Bem-vindo ao Gerenciador de Tarefas!";
    }

    @GetMapping("/conta_user/{nome}") //talvez completar depois ocm o nome do user em questao se a gente conseguir fazer - tratamento de query
    public String contUser(@PathVariable String nome) {
        return "Bem-vindo a sua conta" + nome + "!"; 
    }

    @GetMapping("/editar_conta_user/{nome}") 
    public String editarContaUser(@PathVariable String nome) { //duvida
        return "o user edita dados da sua conta," + nome;
    }

    //alguns testes de rota com atributo especifico da task
    @PostMapping("/task_user")
    public TaskUser taskUser(@RequestBody TaskUser u){
        return u;
    }
}
