package com.alexis.proyecto.gestionusuariosroles.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexis.proyecto.gestionusuariosroles.domain.Rol;
import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;
import com.alexis.proyecto.gestionusuariosroles.repositories.RolRepository;
import com.alexis.proyecto.gestionusuariosroles.repositories.UsuarioRepository;
import com.alexis.proyecto.gestionusuariosroles.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private RolRepository rr;

  @Override
  public List<Usuario> getUsuarios() {
    List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
    List<Rol> roles = (List<Rol>) rr.findAll();

    List<Rol> rolesUser = roles.stream()
        .filter(rol -> rol.getNombreRol().equals("user"))
        .collect(Collectors.toList());

    List<Usuario> usuariosFiltrados = usuarios.stream()
        .filter(usuario -> usuario.getRoles().stream()
            .anyMatch(rol -> rolesUser.contains(rol)))
        .collect(Collectors.toList());
    return usuariosFiltrados;
  }

  @Override
  public Map<String, Object> getDatosUsuario(Authentication authentication) {
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new AccessDeniedException("El usuario no está autenticado.");
    }

    String email = authentication.getName();
    Usuario usuario = usuarioRepository.findByEmail(email)
        .filter(Usuario::getActivo)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

    String rol = authentication.getAuthorities().iterator().next().getAuthority().toUpperCase();

    Map<String, Object> datos = new HashMap<>();
    datos.put("nombre", usuario.getNombre());
    datos.put("rol", rol);
    return datos;
  }

  @Override
  public Map<String, Object> getDatosAdmin(Authentication authentication) {
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new AccessDeniedException("El usuario no está autenticado.");
    }

    String email = authentication.getName();
    Usuario usuario = usuarioRepository.findByEmail(email)
        .filter(Usuario::getActivo)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

    String rol = authentication.getAuthorities().iterator().next().getAuthority().toUpperCase();

    Map<String, Object> datos = new HashMap<>();
    datos.put("nombre", usuario.getNombre());
    datos.put("rol", rol);
    return datos;
  }
}
