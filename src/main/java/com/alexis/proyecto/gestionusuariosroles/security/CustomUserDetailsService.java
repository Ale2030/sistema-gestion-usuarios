package com.alexis.proyecto.gestionusuariosroles.security;

import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;
import com.alexis.proyecto.gestionusuariosroles.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class CustomUserDetailsService implements UserDetailsService {

        private UsuarioRepository ur;

        @Transactional
        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Usuario usuario = ur.findByEmail(email)
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
