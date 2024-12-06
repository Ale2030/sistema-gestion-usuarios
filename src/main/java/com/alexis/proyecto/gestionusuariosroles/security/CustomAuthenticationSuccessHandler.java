package com.alexis.proyecto.gestionusuariosroles.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
            response.sendRedirect("usuario/admin/dashboard");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("user"))) {
            response.sendRedirect("usuario/dashboard");
        } else {
            response.sendRedirect("/index");
        }

    }
}
