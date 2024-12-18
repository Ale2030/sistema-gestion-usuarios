package com.alexis.proyecto.gestionusuariosroles.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexis.proyecto.gestionusuariosroles.domain.Rol;

/**
 * Repositorio para gestionar las operaciones CRUD,
 * de la entidad {@link Rol}
 * 
 * @author Alex
 */
@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {

}
