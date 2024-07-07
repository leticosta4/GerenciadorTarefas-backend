package com.nossogrupo.GerenciadorTarefas.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository; 
// import org.springframework.stereotype.Repository;

import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.model.projection.UserProjection;

public interface TaskUserRepository extends JpaRepository<TaskUser, Long>{
    boolean existsByUserId(Long userId);
    ArrayList <TaskUser> findAllBy();
    UserProjection findByUserId(Long userId);
    void removeByUserId(Long userId);
}
