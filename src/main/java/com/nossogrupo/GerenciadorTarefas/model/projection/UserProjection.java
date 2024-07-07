package com.nossogrupo.GerenciadorTarefas.model.projection;

import java.util.List;

public interface UserProjection {
    long getUserId();
    String getNome();
    String getEmail();
    List<TarefaProjection> getListaTarefasUser(); 
}
