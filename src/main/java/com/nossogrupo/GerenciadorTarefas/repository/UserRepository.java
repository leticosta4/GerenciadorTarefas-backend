package com.nossogrupo.GerenciadorTarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nossogrupo.GerenciadorTarefas.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
