package com.alexis.proyecto.gestionusuariosroles.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;

/**
 * Repositorio para gestionar las operaciones CRUD,
 * de la entidad {@link Usuario}
 * 
 * @author Alex
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    /**
     * Metodo para buscar el usuario por email
     * 
     * @param email email del usuario
     * @return retorna el Usuario
     */
    Optional<Usuario> findByEmail(String email);
}
