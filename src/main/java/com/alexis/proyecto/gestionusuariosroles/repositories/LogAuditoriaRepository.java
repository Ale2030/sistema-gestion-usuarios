package com.alexis.proyecto.gestionusuariosroles.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.alexis.proyecto.gestionusuariosroles.domain.LogAuditoria;

/**
 * Repositorio para gestionar las operaciones CRUD,
 * de la entidad {@link LogAuditoria}
 * @author Alex
 */
@Repository
public interface LogAuditoriaRepository extends CrudRepository<LogAuditoria, Integer> {

}
