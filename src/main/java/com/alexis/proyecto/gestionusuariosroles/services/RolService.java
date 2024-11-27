package com.alexis.proyecto.gestionusuariosroles.services;

import java.util.List;

import com.alexis.proyecto.gestionusuariosroles.domain.Rol;

public interface RolService {
    List<Rol>getRoles();
    List<Integer>getRolesByNombreRol(String nombre);
}
