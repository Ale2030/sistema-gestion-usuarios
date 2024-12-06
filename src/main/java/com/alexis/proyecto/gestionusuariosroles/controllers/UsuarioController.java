package com.alexis.proyecto.gestionusuariosroles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;
import com.alexis.proyecto.gestionusuariosroles.domain.UsuarioRol;
import com.alexis.proyecto.gestionusuariosroles.services.impl.UsuarioRolServiceImpl;
import com.alexis.proyecto.gestionusuariosroles.services.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usr;

    @Autowired
    private UsuarioRolServiceImpl ursi;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("admin"))) {
            return "admin-dashboard";
        }
        return "redirect:/access-denied";
    }

    @GetMapping("/dashboard")
    public String userDashboard(Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("user"))) {
            return "usuario-dashboard";
        }
        return "redirect:/access-denied";
    }

}
