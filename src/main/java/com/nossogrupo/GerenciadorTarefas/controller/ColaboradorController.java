package com.nossogrupo.GerenciadorTarefas.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.Colaborador;
import com.nossogrupo.GerenciadorTarefas.repository.ColaboradorRepository;

import jakarta.transaction.Transactional;

@RestController
public class ColaboradorController {
    @Autowired private ColaboradorRepository acaoColaborador;

    //TALVEZ TAMBEM PRECISEM DO ID DO COLABORADOR POR QUESTAO DE SEGURANCA

    @GetMapping("/api/sobre") 
    public ArrayList<Colaborador> sobre() {
        System.out.println("sobre os programadores");
        ArrayList<Colaborador> listaColaboradores = acaoColaborador.findAll();
        int quantidadeColaboradores = listaColaboradores.size();
        System.out.println("são " + quantidadeColaboradores + "colaboradores");
        return  listaColaboradores;
    }

    @PostMapping("/api/add_colaborador") 
    @Transactional
    public Colaborador criarColaborador(@RequestBody Colaborador novoColaborador) {
        System.out.println("adicionando um colaborador");
        return acaoColaborador.save(novoColaborador);
    }

    @PutMapping("/api/editar_colaborador") //talvez tenha que mudar depois p especificar na rota
    @Transactional
    public Colaborador editarColaborador(@RequestBody Colaborador colaborador) {
        System.out.println("edicao do colaborador. ID: " + colaborador.getColaboradorId() + "- nome: " + colaborador.getNome());
        return acaoColaborador.save(colaborador);
    }

    @DeleteMapping("/api/remover_colaborador/{colaboradorId}") 
    @Transactional
    public void removerColaborador(@PathVariable Long colaboradorId) {
        System.out.println("removendo o colaborador com ID: "+ colaboradorId);
        acaoColaborador.removeByColaboradorId(colaboradorId);
    }
    
}
