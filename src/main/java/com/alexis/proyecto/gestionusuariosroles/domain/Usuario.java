package com.alexis.proyecto.gestionusuariosroles.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Clase representa la entidad Usuario,
 * Administra los usuarios
 * @author Alex
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {
    /*ID unico del usuario.*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    /*Nombre del usuario.*/
    @Column(length = 25, nullable = false)
    private String nombre;
    /*Email del usuario.*/
    @Column(length = 60, nullable = false, unique = true)
    private String email;
    /*Contraseña del usuario.*/
    @Column(nullable = false)
    private String password;
    /*Campo para saber si el registro esta activo*/
    @Column(nullable = false)
    private Boolean activo = true;
    /*Fecha de creacion del usuario*/
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
    /*Fecha de actualizacion del usuario.*/
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    /*Roles que puede tener el usuario.*/
    @ManyToMany(fetch = FetchType.LAZY) 
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private List<Rol> roles;

    @Override
    public String toString() {
        return "Usuario{idUsuario=" + idUsuario + ", nombre='" + nombre + "', email='" + email + "', roles='" + roles
                + "', activo=" + activo
                + "}";
    }

}
