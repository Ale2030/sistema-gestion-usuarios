package com.alexis.proyecto.gestionusuariosroles.domain;

import com.alexis.proyecto.gestionusuariosroles.enums.Accion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase representa entidad de permisos del usuario,
 * Administra cuantos permisos hay y sobre que rol.
 * @author Alex
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permisos")
public class Permiso {
    /* ID unico del permiso del usuario */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermiso;
    /* Acciones que puede ejercer el usuario */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Accion accion;
    /* A que accion pertenece el rol */
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

}
