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
@Table(name = "logs_auditoria")
public class LogAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_log;
    private int id_usuario;
    private Accion accion;
    private String tabla_afectada;
    @Column(name = "fecha_creacion", updatable = false, nullable = false)
    private java.sql.Timestamp fechaCreacion;
}
