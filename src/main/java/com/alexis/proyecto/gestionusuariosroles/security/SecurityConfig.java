package com.alexis.proyecto.gestionusuariosroles.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import lombok.AllArgsConstructor;
/**
 * Clase de configuracion gestiona la seguridad y autorizacion de la app,
 * Define las reglas de acceso a las rutas, el cifrado de contraseñas, y la autenticación del usuario.
 * @author Alex
 */
@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    /**
     * Filtro para manejar solicitudes HTTP con métodos como PUT o DELETE mediante el atributo `_method` oculto en formularios.
     * 
     * @return Un {@link HiddenHttpMethodFilter} para habilitar métodos HTTP ocultos.
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
    
    /**
     * 
     * @param http Configuracion de seguridad.
     * @return retorna una instancia de las configuraciones.
     * @throws Exception error si sucede algo al construir la configuracion.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("admin")
                        .requestMatchers("/user/**").hasAuthority("user")
                        .requestMatchers("/usuario/remove").hasAuthority("admin")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());

        return http.build();
    }
    /**
     * Bean para encryptar
     * @return retorna una cadena encriptada
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Proveedor de autenticacion que utiliza el servicio de detalles,
     * de usuario y el codificador de contraseñas.
     * @return un {@link DaoAuthenticationProvider}configurado.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    /**
     * Configura y retorna el {@link AuthenticationManager},
     * para gestionar el proceso de autenticación.
     * @param http Configuracion de seguridad HTTP.
     * @return Una instancia de {@link AuthenticationManager}
     * @throws Exception Si hay error al configurar el AuthenticationManager
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }
}
