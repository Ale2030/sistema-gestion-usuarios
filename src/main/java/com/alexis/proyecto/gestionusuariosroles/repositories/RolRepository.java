package com.alexis.proyecto.gestionusuariosroles.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexis.proyecto.gestionusuariosroles.models.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {

}
