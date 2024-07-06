package com.nossogrupo.GerenciadorTarefas.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.Colaborador;
import com.nossogrupo.GerenciadorTarefas.repository.ColaboradorRepository;

@RestController
public class ColaboradorController {
    @Autowired 
    private ColaboradorRepository acaoColaborador;

    @GetMapping("/sobre") 
    public ArrayList<Colaborador> sobre() {
        //provavelmente vai usar o findall
        System.out.println("sobre os programadores");
        return acaoColaborador.findAll();
    }

    @PostMapping("/add_colaborador") 
    public Colaborador criarColaborador(@RequestBody Colaborador novoColaborador) {
        System.out.println("adicionando um colaborador");
        //implementar dpeois talvez com funcoes extras
        return acaoColaborador.save(novoColaborador);
    }

    @PutMapping("/editar_colaborador") //talvez tenha que mudar depois p especificar na rota
    public Colaborador editarColaborador(@RequestBody Colaborador colaborador) {
        System.out.println("edicao do colaborador. ID: " + colaborador.getColaboradorId() + "- nome: " + colaborador.getNome());
        return acaoColaborador.save(colaborador);
    }

    @GetMapping("/remover_colaborador/{nome}") 
    public String removerColaborador(@PathVariable String nome) {
        return "removendo um colaborador: " + nome;
    }

    
}
