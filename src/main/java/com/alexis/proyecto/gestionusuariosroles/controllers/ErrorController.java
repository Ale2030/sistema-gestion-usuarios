package com.alexis.proyecto.gestionusuariosroles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para gestionar la vista de access-denied.
 */
@Controller
public class ErrorController {
    /**
     * Muestra la vista access-denied.
     * 
     * @return la vista access-denied.
     */
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
