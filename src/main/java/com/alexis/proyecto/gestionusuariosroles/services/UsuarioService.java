package com.alexis.proyecto.gestionusuariosroles.services;

import java.util.List;
import java.util.Map;
import org.springframework.security.core.Authentication;

import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;

public interface UsuarioService {

    List<Usuario> getUsuarios();

    Map<String, Object> getDatosUsuario(Authentication authentication);

    Map<String, Object> getDatosAdmin(Authentication authentication);

}
