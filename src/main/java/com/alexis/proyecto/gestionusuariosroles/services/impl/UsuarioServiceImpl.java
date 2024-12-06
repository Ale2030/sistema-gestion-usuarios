package com.alexis.proyecto.gestionusuariosroles.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;
import com.alexis.proyecto.gestionusuariosroles.repositories.UsuarioRepository;
import com.alexis.proyecto.gestionusuariosroles.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private UsuarioRepository ur;

  @Override
  public List<Usuario> getUsuarios() {
    List<Usuario> usuarios = (List<Usuario>) ur.findAll();
    return usuarios;
  }

}
