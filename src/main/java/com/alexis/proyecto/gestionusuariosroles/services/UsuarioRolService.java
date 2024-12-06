package com.alexis.proyecto.gestionusuariosroles.services;

import java.util.List;

import com.alexis.proyecto.gestionusuariosroles.domain.UsuarioRol;

public interface UsuarioRolService {
    List<UsuarioRol> getUsuarioRolServices();

    List<UsuarioRol> getAdmin();

    List<UsuarioRol> getUser();
}
