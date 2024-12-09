package com.alexis.proyecto.gestionusuariosroles.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.proyecto.gestionusuariosroles.domain.Rol;
import com.alexis.proyecto.gestionusuariosroles.repositories.RolRepository;
import com.alexis.proyecto.gestionusuariosroles.services.RolService;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rr;

    @Override
    public List<Rol> getRoles() {
        List<Rol> roles = (List<Rol>) rr.findAll();
        return roles;
    }

    @Override
    public List<Integer> getRolesByNombreRol(String nombre) {
        // TODO Auto-generated method stub
        return null;
    }

}
