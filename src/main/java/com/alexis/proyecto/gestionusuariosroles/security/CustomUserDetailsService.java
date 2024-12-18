package com.alexis.proyecto.gestionusuariosroles.security;

import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;
import com.alexis.proyecto.gestionusuariosroles.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
* Servicio para gestionar el inicio sesion del usuario y asignarle su autorizacion
* @author Alex
*/
@AllArgsConstructor
@Service

public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UsuarioRepository ur;

        /**
         * Metodo construye un objeto {@link User} que contiene el
         * nombre, contraseña y su rol asociado al {@link Usuario}.
         * 
         * @param email correo elenctronico del usuario.
         * @return Un objeto {@link UserDetail} con el usuario y su autorizacion.
         */
        @Transactional
        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Usuario usuario = ur.findByEmail(email)
                                .filter(Usuario::getActivo)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "Usuario no encontrado con email: " + email));

                List<GrantedAuthority> authorities = usuario.getRoles().stream()
                                .map(rol -> {
                                        return new SimpleGrantedAuthority(rol.getNombreRol());
                                })
                                .collect(Collectors.toList());
                return User.builder()
                                .username(usuario.getEmail())
                                .password(usuario.getPassword())
                                .authorities(authorities)
                                .build();
        }

}
