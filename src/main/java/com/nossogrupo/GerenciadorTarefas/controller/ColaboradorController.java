package com.nossogrupo.GerenciadorTarefas.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nossogrupo.GerenciadorTarefas.model.Colaborador;
import com.nossogrupo.GerenciadorTarefas.repository.ColaboradorRepository;

//import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*")
public class ColaboradorController {
    @Autowired private ColaboradorRepository acaoColaborador;

    @GetMapping({"/GerenciadorTarefas/sobre", "/GerenciadorTarefas/{userId}/sobre"})  
    public ArrayList<Colaborador> sobre(@PathVariable(required = false) String userId) {
        if (userId != null) {
            try {
                Long userIdLong = Long.parseLong(userId);
                System.out.println("USER COM ID: " + userIdLong + " LOGADO");
            } catch (NumberFormatException e) {
                System.out.println("VALOR INVALIDO para userId: " + userId);
            }
        } else {  System.out.println("NINGUEM LOGADO"); }

        System.out.println("sobre os programadores");
        ArrayList<Colaborador> listaColaboradores = acaoColaborador.findAll();
        int quantidadeColaboradores = listaColaboradores.size();
        System.out.println("são " + quantidadeColaboradores + "colaboradores");
        return  listaColaboradores;
    }

    // @PostMapping("/GerenciadorTarefas/add_colaborador") 
    // @Transactional
    // public Colaborador criarColaborador(@RequestBody Colaborador novoColaborador) {
    //     System.out.println("adicionando um colaborador");
    //     return acaoColaborador.save(novoColaborador);
    // }

    // @PutMapping("/GerenciadorTarefas/{colaboradorId}/editar_colaborador") //faltou especificar o id do colaborador
    // @Transactional
    // public Colaborador editarColaborador(@PathVariable Long colaboradorId, @RequestBody Colaborador colaborador) {
    //     System.out.println("edicao do colaborador. ID: " + colaboradorId + "- nome: " + colaborador.getNome());
    //     return acaoColaborador.save(colaborador);
    // }

    // @DeleteMapping("/GerenciadorTarefas/{colaboradorId}/remover_colaborador") 
    // @Transactional
    // public void removerColaborador(@PathVariable Long colaboradorId) {
    //     System.out.println("removendo o colaborador com ID: "+ colaboradorId);
    //     acaoColaborador.removeByColaboradorId(colaboradorId);
    // }
    
}
