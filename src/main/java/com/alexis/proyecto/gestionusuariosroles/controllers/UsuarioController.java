package com.alexis.proyecto.gestionusuariosroles.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexis.proyecto.gestionusuariosroles.services.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usr;

    @GetMapping("/card")
    public String getCard(Model model) {
        return "card";
    }

    @GetMapping("/dashboard")
    public String userDashboard(Authentication authentication, Model model) {
        Map<String, Object> user = usr.getDatosUsuario(authentication);
        model.addAllAttributes(user);
        if (user.containsValue("USER")) {
            return "usuario-dashboard";
        } else {
            return "redirect:/access-denied";
        }
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Authentication authentication, Model model) {
        Map<String, Object> admin = usr.getDatosAdmin(authentication);
       if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("admin"))) {
            model.addAttribute("users", usr.getUsuarios());
            model.addAllAttributes(admin);
            return "admin-dashboard";
        } else {
            return "redirect:/access-denied";
        }
    }

}
