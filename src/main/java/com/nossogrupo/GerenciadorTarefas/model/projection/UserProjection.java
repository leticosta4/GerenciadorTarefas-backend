package com.nossogrupo.GerenciadorTarefas.model.projection;

import java.util.List;

import com.nossogrupo.GerenciadorTarefas.model.Tarefa;

public interface UserProjection {
    String getNome();
    String getEmail();
    List<Tarefa> getListaTarefasUser();
}
