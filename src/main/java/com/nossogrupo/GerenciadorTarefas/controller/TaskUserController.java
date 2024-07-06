package com.nossogrupo.GerenciadorTarefas.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.model.projection.TarefaProjection;
import com.nossogrupo.GerenciadorTarefas.model.projection.UserProjection;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

@RestController
public class TaskUserController {

    @Autowired TaskUserRepository userAcao;
    //USAR POST EM TODAS AS ROTAS QUE PRECISEM DE DADOS SEM SER VIA URL

    @GetMapping("/{userId}/atividades")
    // public ArrayList<TarefaProjection> atividades(@PathVariable Long userId) {
    //     System.out.println("Bem-vindo ao Gerenciador de Tarefas! user com ID:" + userId);
    //     userAcao.findByUserId(userId);
    //     return userAcao.findAllBy();
    // }

    @PostMapping("/cadastro")
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
        System.out.println("Bem-vindo a sua conta do user com ID:" + userId);
        return userAcao.findByUserId(userId); 
    }

    @PutMapping("/editar_conta_user") //acho que tem que passar o id especifico do user talvez
    public TaskUser editarContaUser(@RequestBody TaskUser user) { 
        System.out.println("o user edita dados da sua conta. ID: " + user.getUserId() + " - nome: " + user.getNome());
        // String msg = userServico.editarUser(user, userAcao);
        // return msg;
        return userAcao.save(user);
    }

    //alguns testes de rota com atributo especifico da task
    @PostMapping("/task_user")
    public TaskUser taskUser(@RequestBody TaskUser u){
        return u;
    }
}
