package com.nossogrupo.GerenciadorTarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;  
import java.util.ArrayList;

import com.nossogrupo.GerenciadorTarefas.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{
    boolean existsByColaboradorId(Long colaboradorId);
    ArrayList <Colaborador> findAll();
    void removeByColaboradorId(Long colaboradorId);
}
