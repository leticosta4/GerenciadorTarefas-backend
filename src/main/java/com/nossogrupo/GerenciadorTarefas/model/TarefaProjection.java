package com.nossogrupo.GerenciadorTarefas.model;

import java.time.LocalDate;

/*usando essa projecao p evitar o aninhamento do user na exibicao das tarefas.
por padrao ele aninha
 */
public interface TarefaProjection {
    // Long getTarefaId();
    String getTitulo();
    String getDescricao();
    String getStatus();
    LocalDate getDataCriacao();
    LocalDate getDataFinal();
    // String getCorFundo();
    String getLocal();
}
