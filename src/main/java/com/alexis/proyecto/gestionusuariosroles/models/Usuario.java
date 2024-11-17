package com.alexis.proyecto.gestionusuariosroles.models;

import com.alexis.proyecto.gestionusuariosroles.enums.Roles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_usuario;
    private String nombre;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles rol;
    private boolean activo = true;
    @Column(name = "fecha_creacion", updatable = false, nullable = false)
    private java.sql.Timestamp fechaCreacion; 
    @Column(name = "fecha_actualizacion", insertable = false)
    private java.sql.Timestamp fechaActualizacion;
}
