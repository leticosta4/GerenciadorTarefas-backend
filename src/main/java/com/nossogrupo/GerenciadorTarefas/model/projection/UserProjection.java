package com.nossogrupo.GerenciadorTarefas.model.projection;

import java.util.List;

public interface UserProjection {
    long getUserId();
    String getNome();
    String getEmail();
    //teste : mudando de List <Tarefa> para List<TarefaProjection> para tentar resolver aninhamento
    List<TarefaProjection> getListaTarefasUser(); //REVISAR USO
}
