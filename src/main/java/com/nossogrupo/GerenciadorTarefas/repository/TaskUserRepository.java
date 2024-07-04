package com.nossogrupo.GerenciadorTarefas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;

public interface TaskUserRepository extends CrudRepository<TaskUser, Long>{
    
}
