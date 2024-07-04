package com.nossogrupo.GerenciadorTarefas.controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GeralController {
    @GetMapping("/")
    public String home() {
        return "oi esse é o landing page, o user n ta logado";
    }

    @GetMapping("/sobre") 
    public String sobre() {
        return "sobre os programadores";
    }
}
