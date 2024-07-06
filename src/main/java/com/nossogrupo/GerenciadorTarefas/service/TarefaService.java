package com.nossogrupo.GerenciadorTarefas.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.repository.TaskUserRepository;

@Service
public class TarefaService {
    private static TaskUser identificaUserPorIdTask(Tarefa tarefa, TaskUserRepository userAcao){
        TaskUser userEspecifico = new TaskUser();
        ArrayList<TaskUser> listaUsers = userAcao.findAllBy();
        Boolean encontrou = false;

        for(int i = 0; i < listaUsers.size(); i++){
            if (tarefa.getUser().getUserId() == listaUsers.get(i).getUserId()){
                userEspecifico = listaUsers.get(i);
                encontrou = true;
                break;
            } 
        }
        if(!encontrou){System.out.println("USER NAO ENCONTRADA");} else{System.out.println("USER OK!");}
        return userEspecifico;
    }

    public void adicionarTaskListaUser(Tarefa tarefa, TaskUserRepository userAcao){
        identificaUserPorIdTask(tarefa, userAcao).getListaTarefasUser().add(tarefa);
    }
}
