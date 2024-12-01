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
        List<Integer> idAdmin = rsi.getRolesByNombreRol("admin");
        List<UsuarioRol> usuarioRols = (List<UsuarioRol>) urr.findAll();

        List<UsuarioRol> admin = usuarioRols
                .stream()
                .filter(usuarioRol -> idAdmin.contains(usuarioRol.getId_rol()))
                .toList();
        return admin;
    }

    @Override
    public List<UsuarioRol> getUser() {
        List<Integer> idUser = rsi.getRolesByNombreRol("user");
        List<UsuarioRol> usuarioRols = (List<UsuarioRol>) urr.findAll();

        List<UsuarioRol> user = usuarioRols
                .stream()
                .filter(usuarioRol -> idUser.contains(usuarioRol.getId_rol()))
                .toList();
        return user;

    }

}
