package com.nossogrupo.GerenciadorTarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nossogrupo.GerenciadorTarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{
    
}
