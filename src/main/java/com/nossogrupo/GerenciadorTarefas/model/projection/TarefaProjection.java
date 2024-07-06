package com.nossogrupo.GerenciadorTarefas.model.projection;

import java.time.LocalDate;

/*usando essa projecao p evitar o aninhamento do user na exibicao das tarefas.
por padrao ele aninha
 */
public interface TarefaProjection {
    String getTitulo();
    String getDescricao();
    String getStatus();
    LocalDate getDataCriacao();
    LocalDate getDataFinal();
    String getLocal();
}
