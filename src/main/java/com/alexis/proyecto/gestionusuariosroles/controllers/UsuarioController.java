package com.alexis.proyecto.gestionusuariosroles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alexis.proyecto.gestionusuariosroles.repositories.UsuarioRepository;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository ur;

    
}
