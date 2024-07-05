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
