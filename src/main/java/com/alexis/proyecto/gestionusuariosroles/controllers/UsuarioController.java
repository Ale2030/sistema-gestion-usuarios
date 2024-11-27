package com.alexis.proyecto.gestionusuariosroles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;
import com.alexis.proyecto.gestionusuariosroles.domain.UsuarioRol;
import com.alexis.proyecto.gestionusuariosroles.services.impl.UsuarioRolServiceImpl;
import com.alexis.proyecto.gestionusuariosroles.services.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioServiceImpl usr;

    @Autowired
    private UsuarioRolServiceImpl ursi;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok(usr.getUsuarios());
    }
    @GetMapping("/usuariorol")
    public ResponseEntity<List<UsuarioRol>> getUsuarioRol(){
        return ResponseEntity.ok(ursi.getUsuarioRolServices());
    }
    @GetMapping("/admin")
    public ResponseEntity<List<UsuarioRol>> getAdmin(){
        return ResponseEntity.ok(ursi.getAdmin());
    }
    @GetMapping("/user")
    public ResponseEntity<List<UsuarioRol>> getUser(){
        return ResponseEntity.ok(ursi.getUser());
    }

}
