package com.nossogrupo.GerenciadorTarefas.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class GeralController {

    //ver como fazer a instancia de objetos padrao p o banco

    @GetMapping("/")
    public String home() {
        return "oi esse é o landing page, o user n ta logado";
    }
}
