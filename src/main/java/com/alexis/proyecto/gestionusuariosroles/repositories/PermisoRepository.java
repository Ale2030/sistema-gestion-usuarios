package com.alexis.proyecto.gestionusuariosroles.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexis.proyecto.gestionusuariosroles.domain.Permiso;
/**
 * Repositorio para gestionar las operaciones CRUD,
 * de la entidad {@link Permiso}
 * @author Alex
 */
@Repository
public interface PermisoRepository extends CrudRepository<Permiso, Integer> {

}
