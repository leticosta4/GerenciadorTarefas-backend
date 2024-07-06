package com.nossogrupo.GerenciadorTarefas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.model.projection.TarefaProjection;
import com.nossogrupo.GerenciadorTarefas.model.projection.UserProjection;
public interface TaskUserRepository extends CrudRepository<TaskUser, Long>{
    UserProjection findByUserId(Long userId);
}
