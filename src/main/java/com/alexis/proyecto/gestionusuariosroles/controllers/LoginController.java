package com.alexis.proyecto.gestionusuariosroles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Controlador para gestionar la vista de login.
 */
@Controller
public class LoginController {
    /**
     * Muestra la vista de login.
     * 
     * @return la vista de login.
     */
    @GetMapping("/login")
    public String mostrarPagina() {
        return "login";
    }
}
