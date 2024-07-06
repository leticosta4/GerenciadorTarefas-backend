package com.nossogrupo.GerenciadorTarefas.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.model.projection.UserProjection;
public interface TaskUserRepository extends CrudRepository<TaskUser, Long>{
    ArrayList <TaskUser> findAllBy();
    UserProjection findByUserId(Long userId);
}
