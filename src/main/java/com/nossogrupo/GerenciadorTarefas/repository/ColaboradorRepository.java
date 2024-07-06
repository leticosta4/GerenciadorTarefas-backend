package com.nossogrupo.GerenciadorTarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.nossogrupo.GerenciadorTarefas.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{
    ArrayList <Colaborador> findAll();
    void removeByColaboradorId(Long colaboradorId);
}
