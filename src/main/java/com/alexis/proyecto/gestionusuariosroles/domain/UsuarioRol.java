package com.alexis.proyecto.gestionusuariosroles.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * Clase representa la relacion entre usuarios-roles,
 * Al ser una relacion ManyToMany de {@link Rol} y {@link Usuario},
 * esta entidad actúa como una tabla intermedia para gestionar y controlar
 * dicha relación.
 * 
 * @author Alex
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios_roles")
public class UsuarioRol {
    /*
     * Clase interna que define la clave primaria compuesta para la tabla
     * usuarios_roles.
     */
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UsuarioRolId implements Serializable {
        private Integer idUsuario;
        private Integer idRol;
    }

    /* Clave primario compuesta. */
    @EmbeddedId
    private UsuarioRolId id;
    /* Entidad de usuario asocioada a esta relacion. */
    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    /* Entidad de rol asociada a esta relacion. */
    @ManyToOne
    @MapsId("idRol")
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}
