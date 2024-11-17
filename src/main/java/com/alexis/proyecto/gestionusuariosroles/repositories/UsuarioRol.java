package com.alexis.proyecto.gestionusuariosroles.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRol extends CrudRepository<UsuarioRol, Integer> {

}
