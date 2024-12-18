package com.alexis.proyecto.gestionusuariosroles.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.proyecto.gestionusuariosroles.domain.Rol;
import com.alexis.proyecto.gestionusuariosroles.repositories.RolRepository;
import com.alexis.proyecto.gestionusuariosroles.services.RolService;

/**
 * Servicio implementa la interface {@link RolService},
 * gestiona logica de negocio {@link Rol}.
 *  @author Alex
 */
@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rr;

    @Override
    public List<Rol> getRoles() {
        List<Rol> roles = (List<Rol>) rr.findAll();
        return roles;
    }

}
