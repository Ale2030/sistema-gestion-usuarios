package com.alexis.proyecto.gestionusuariosroles.domain;

import com.alexis.proyecto.gestionusuariosroles.enums.Accion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permisos")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermiso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Accion accion;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

}
