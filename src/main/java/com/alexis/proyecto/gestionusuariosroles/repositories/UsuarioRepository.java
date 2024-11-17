package com.alexis.proyecto.gestionusuariosroles.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexis.proyecto.gestionusuariosroles.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
