package com.alexis.proyecto.gestionusuariosroles.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexis.proyecto.gestionusuariosroles.domain.Rol;
import com.alexis.proyecto.gestionusuariosroles.domain.UsuarioRol;

@Repository
public interface UsuarioRolRepository extends CrudRepository<UsuarioRol, Integer> {
    List<Rol> findRolesByUserId(int userId);
}
