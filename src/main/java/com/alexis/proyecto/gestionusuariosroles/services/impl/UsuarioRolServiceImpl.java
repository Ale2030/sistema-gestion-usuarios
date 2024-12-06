package com.alexis.proyecto.gestionusuariosroles.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.proyecto.gestionusuariosroles.domain.UsuarioRol;
import com.alexis.proyecto.gestionusuariosroles.repositories.UsuarioRolRepository;
import com.alexis.proyecto.gestionusuariosroles.services.UsuarioRolService;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService {

    @Autowired
    private UsuarioRolRepository urr;

    @Autowired
    private RolServiceImpl rsi;

    @Override
    public List<UsuarioRol> getUsuarioRolServices() {
        List<UsuarioRol> usuarioRols = (List<UsuarioRol>) urr.findAll();
        return usuarioRols;
    }

    @Override
    public List<UsuarioRol> getAdmin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UsuarioRol> getUser() {
        // TODO Auto-generated method stub
        return null;
    }

}
