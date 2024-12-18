package com.alexis.proyecto.gestionusuariosroles.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase representa la entidad Roles,
 * Administra los roles de cada usuario.
 * @author Alex
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = "nombre_rol"))
public class Rol {
    /*ID unico del rol.*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;
    /*Nombre del rol.*/
    @Column(name = "nombre_rol", length = 20, nullable = false, unique = true)
    private String nombreRol;
    /*Permisos que unen a los roles cada rol con sus permisos */
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Permiso> permisos = new HashSet<>();
    /*Usuarios con los roles*/
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new HashSet<>();

    @Override
    public String toString() {
        return "Rol{idRol=" + idRol + ", nombreRol='" + nombreRol + "'}";
    }

}
