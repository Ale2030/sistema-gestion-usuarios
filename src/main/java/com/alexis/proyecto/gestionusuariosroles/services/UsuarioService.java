package com.alexis.proyecto.gestionusuariosroles.services;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;

import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;

/**
 * Interface de servicio para gestionar al usuario
 * @author Alex
 */
public interface UsuarioService {
    /**
     * Metodo para crear una instancia de {@link Usuario}.
     * 
     * @param usuario usuario de la instancia a crear.
     * @return retorna la instancia creada instancia de usuario.
     */
    Usuario createUsuario(Usuario usuario);

    /**
     * Metodo actualiza los atributos de {@link Usuario}
     * 
     * @param idUsuario ID unico del usuario.
     * @param nombre    nombre a cambiar del usuario.
     * @param email     email a cambiar del usuario.
     */
    void putUsuario(int idUsuario, String nombre, String email);

    /**
     * 
     * @return retorna una lista de {@link Usuario}
     */
    List<Usuario> getUsuarios();

    /**
     * Metodo para obtener la autorizacion y datos del {@link Usuario}
     * 
     * @param authentication Instancia de {@link Authentication}que contiene la
     *                       informacion del usuario
     * @return retorna un{@link Map} con una clave y valor del usuario autenticado.
     */
    Map<String, Object> getDatosUsuario(Authentication authentication);

    /**
     * Obtiene la autorización y los datos del administrador autenticado.
     * 
     * @param authentication Instancia de {@link Authentication} que contiene la
     *                       información del administrador .
     * @return Un {@link Map} que contiene las claves y valores relacionados con el
     *         administrador autenticado.
     */
    Map<String, Object> getDatosAdmin(Authentication authentication);

    /**
     * Metodo cambia el valor de {@link Usuario} activo(activo = false).
     * @param idUsuario ID de {@link Usuario}.
     * @return  El {@link Usuario} desactivado con el valor de activo actualizado.
     */
    Usuario deleteUsuario(int idUsuario);

}
