package com.nossogrupo.GerenciadorTarefas.repository;

//importar o crud repository, a annotation e o modelo referente
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.model.projection.TarefaProjection;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{ //chamar o tipo de dado da chave primaria
    public static final TaskUserRepository userAcao = null;
    ArrayList <TarefaProjection> findAllBy(); //revisar uso depois
    TarefaProjection findByTarefaId(Long tarefaId); 
    List<TarefaProjection> findByUserUserId(Long userId);
    void deleteByTarefaId(Long tarefaId);

    //talvez precisem de mais com filtros
    
    List<TarefaProjection> findByUserUserIdAndStatus(Long userId, String status);
    List<TarefaProjection> findByUserUserIdAndStatusOrderByDataCriacao(Long userId, String status); 
    List<TarefaProjection> findByUserUserIdAndStatusOrderByDataCriacaoDesc(Long userId, String status); 
    List<TarefaProjection> findByUserUserIdAndStatusOrderByDataFinal(Long userId, String status);
    List<TarefaProjection> findByUserUserIdAndStatusOrderByDataFinalDesc(Long userId, String status);
}
