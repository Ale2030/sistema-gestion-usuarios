package com.alexis.proyecto.gestionusuariosroles.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.alexis.proyecto.gestionusuariosroles.domain.Rol;
import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;
import com.alexis.proyecto.gestionusuariosroles.repositories.UsuarioRepository;
import com.alexis.proyecto.gestionusuariosroles.services.UsuarioService;

/**
 * Servicio implementa la interface {@link UsuarioService},
 * gestiona logica de negocio del {@link Usuario}.
 *  @author Alex
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private RolServiceImpl rsi;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Usuario createUsuario(Usuario usuario) {
    String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
    usuario.setPassword(encryptedPassword);
    return usuarioRepository.save(usuario);
  }

  @Override
  public List<Usuario> getUsuarios() {
    List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
    List<Rol> roles = rsi.getRoles();

    List<Rol> rolesUser = roles.stream()
        .filter(rol -> rol.getNombreRol().equals("user"))
        .collect(Collectors.toList());

    List<Usuario> usuariosFiltrados = usuarios.stream()
        .filter(usuario -> usuario.getRoles().stream()
            .anyMatch(rol -> rolesUser.contains(rol)))
        .filter(Usuario::getActivo)
        .collect(Collectors.toList());
    return usuariosFiltrados;
  }

  /**
   * Metodo privado que obtiene el nombre y rol del usuario autenticado.
   * 
   * @param authentication authentication Instancia de {@link Authentication}
   *                       que contiene la información del usuario.
   * @return Un {@link Map} con los datos del usuario, incluyendo su nombre y rol
   */
  private Map<String, Object> getRolandName(Authentication authentication) {
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
  public Map<String, Object> getDatosUsuario(Authentication authentication) {
    return getRolandName(authentication);
  }

  @Override
  public Map<String, Object> getDatosAdmin(Authentication authentication) {
    return getRolandName(authentication);
  }

  @Override
  public Usuario deleteUsuario(int idUsuario) {
    Usuario usuario = usuarioRepository.findById(idUsuario).get();
    usuario.setActivo(false);
    usuarioRepository.save(usuario);
    return usuario;

  }

  @Override
  public void putUsuario(int idUsuario, String nombre, String email) {
    Usuario usuario = usuarioRepository.findById(idUsuario)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    usuario.setNombre(nombre);
    usuario.setEmail(email);
    usuarioRepository.save(usuario);
  }

  /**
   * Metodo que obtiene la instancia de {@link Usuario} autenticado actualmente.
   * 
   * @return El usuario autenticado como instancia de {@link Usuario}.
   */
  public Usuario getUsuarioAutenticadoId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    Usuario usuario = usuarioRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(
            "Usuario no encontrado: " + username));
    return usuario;
  }

}
