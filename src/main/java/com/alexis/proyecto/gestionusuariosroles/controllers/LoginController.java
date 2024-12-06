package com.alexis.proyecto.gestionusuariosroles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarPagina() {
        return "login";
    }
}
