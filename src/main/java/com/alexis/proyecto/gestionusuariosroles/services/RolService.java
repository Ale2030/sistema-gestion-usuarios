package com.alexis.proyecto.gestionusuariosroles.services;

import java.util.List;

import com.alexis.proyecto.gestionusuariosroles.domain.Rol;
/**
 * Interface de servicio para la gestion de roles.
 * @author Alex
 */
public interface RolService {
    /** 
     * @return retorna una lista de los roles.
     */
    List<Rol> getRoles();

}
