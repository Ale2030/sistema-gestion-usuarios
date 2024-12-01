package com.alexis.proyecto.gestionusuariosroles.domain;

import com.alexis.proyecto.gestionusuariosroles.enums.Accion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permisos")
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_permiso;
    private Accion accion;
}
