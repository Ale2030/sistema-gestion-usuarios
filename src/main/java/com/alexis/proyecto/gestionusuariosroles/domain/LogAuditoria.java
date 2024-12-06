package com.alexis.proyecto.gestionusuariosroles.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.alexis.proyecto.gestionusuariosroles.enums.Accion;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "logs_auditoria")
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLog;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Accion accion;

    @Column(name = "tabla_afectada", length = 100, nullable = false)
    private String tablaAfectada;

    @Column(name = "fecha_accion")
    private LocalDateTime fechaAccion;

}
