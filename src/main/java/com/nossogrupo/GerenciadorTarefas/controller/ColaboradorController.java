package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.Colaborador;
import com.nossogrupo.GerenciadorTarefas.repository.ColaboradorRepository;

@RestController
public class ColaboradorController {
    @Autowired 
    private ColaboradorRepository acaoColaborador;

    @GetMapping("/sobre") 
    public String sobre() {
        //provavelmente vai usar o findall
        return "sobre os programadores";
    }

    @PostMapping("/add_colaborador") 
    public Colaborador criarColaborador(@RequestBody Colaborador novoColaborador) {
        System.out.println("adicionando um colaborador");

        //implementar dpeois talvez com funcoes extras

        return acaoColaborador.save(novoColaborador);
    }

    @GetMapping("/remover_colaborador/{nome}") 
    public String removerColaborador(@PathVariable String nome) {
        return "removendo um colaborador: " + nome;
    }

    @GetMapping("/editar_colaborador/{nome}") 
    public String editarColaborador(@PathVariable String nome) {
        return "editando um colaborador: " + nome;
    }
}
