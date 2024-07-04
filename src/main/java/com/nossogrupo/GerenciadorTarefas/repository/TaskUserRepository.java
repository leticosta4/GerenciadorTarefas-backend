package com.nossogrupo.GerenciadorTarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nossogrupo.GerenciadorTarefas.model.TaskUser;

public interface TaskUserRepository extends JpaRepository<TaskUser, Long>{
    
}
