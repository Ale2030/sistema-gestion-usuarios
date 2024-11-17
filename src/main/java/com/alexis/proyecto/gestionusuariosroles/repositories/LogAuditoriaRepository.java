package com.alexis.proyecto.gestionusuariosroles.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexis.proyecto.gestionusuariosroles.models.LogAuditoria;

@Repository
public interface LogAuditoriaRepository extends CrudRepository<LogAuditoria, Integer> {

}
