package com.nossogrupo.GerenciadorTarefas.model.projection;

import java.time.LocalDate;

/*usando essa projecao p evitar o aninhamento do user na exibicao das tarefas.
por padrao ele aninha - spring data JPA no repositorio
 */
public interface TarefaProjection {
    Long getTarefaId(); //rever onde ta usando pq provavelmente vai ter que tirar
    String getTitulo();
    String getDescricao();
    String getStatus();
    LocalDate getDataCriacao();
    LocalDate getDataFinal();
    String getCorFundo();
    String getLocal();
}
