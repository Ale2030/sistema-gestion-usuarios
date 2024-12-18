package com.alexis.proyecto.gestionusuariosroles.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.alexis.proyecto.gestionusuariosroles.enums.Accion;

/**
 * Clase representa la entidad LogAuditoria.
 * Administra la auditoria.
 * @author Alex
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "logs_auditoria")
public class LogAuditoria {
    /* ID unico de la auditoria */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLog;
    /* Usuario que realizo una modificacion a las tablas */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    /* La accion que hizo el usuario */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Accion accion;
    /* Tabla a la que se le genero el cambio */
    @Column(name = "tabla_afectada", length = 100, nullable = false)
    private String tablaAfectada;
    /* Fecha en la que el usuario realizo la accion */
    @Column(name = "fecha_accion")
    private LocalDateTime fechaAccion;

}
