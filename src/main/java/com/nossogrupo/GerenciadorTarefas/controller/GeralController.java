package com.nossogrupo.GerenciadorTarefas.controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GeralController {

    //ver como criar com o autowired p instancia de objetos aqui

    @GetMapping("/")
    public String home() {
        return "oi esse é o landing page, o user n ta logado";
    }
}
