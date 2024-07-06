package com.nossogrupo.GerenciadorTarefas.repository;

//importar o crud repository, a annotation e o modelo referente
import org.springframework.data.repository.CrudRepository; 
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.model.projection.TarefaProjection;

import java.util.List;

public interface TarefaRepository extends CrudRepository<Tarefa, Long>{ //chamar o tipo de dado da chave primaria
    /* util p usar depois:
    save(): Realiza o cadastro ou alteração de registros;
    findAll(): Efetua a listagem de todos os dados de uma tabela;
    deleteById(): Remove uma informação através do identificador.
*/

    ArrayList <TarefaProjection> findAllBy();

    TarefaProjection findByTarefaId(Long tarefaId); 

    ArrayList <Tarefa> findByStatus(String status); //para quando formos filtrar 
    //provavelmente vai precisar de um p find by user e manipular a FK
}
