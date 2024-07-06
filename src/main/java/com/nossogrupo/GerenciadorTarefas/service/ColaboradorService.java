package com.nossogrupo.GerenciadorTarefas.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.nossogrupo.GerenciadorTarefas.model.Colaborador;
import com.nossogrupo.GerenciadorTarefas.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
    public String editarColaborador(Colaborador colaboradorEspecifico, ColaboradorRepository colaboradorAcao){
        ArrayList<Colaborador> listaColaboradores = colaboradorAcao.findAll();
        Boolean encontrou = false;

        for(int i = 0; i < listaColaboradores.size(); i++){
            Colaborador colaboradorDaLista = listaColaboradores.get(i);
            if (colaboradorEspecifico.getColaboradorId() == colaboradorDaLista.getColaboradorId()){
                colaboradorDaLista = colaboradorEspecifico;
                encontrou = true;
                break;
            } 
        }
        if(!encontrou){return "COLABORADOR NAO ENCONTRADO";} else{return "COLABORADOR OK!";}
    }
}
