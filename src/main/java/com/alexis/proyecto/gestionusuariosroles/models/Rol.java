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
@Table(name="roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_rol;
    private Roles nombreRoles;
}
