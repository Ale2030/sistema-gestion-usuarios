package com.alexis.proyecto.gestionusuariosroles.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.proyecto.gestionusuariosroles.domain.LogAuditoria;
import com.alexis.proyecto.gestionusuariosroles.domain.Rol;
import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;
import com.alexis.proyecto.gestionusuariosroles.enums.Accion;
import com.alexis.proyecto.gestionusuariosroles.repositories.LogAuditoriaRepository;
import com.alexis.proyecto.gestionusuariosroles.repositories.RolRepository;
import com.alexis.proyecto.gestionusuariosroles.services.LogAuditoriaService;

/**
 * Servicio que implementa la interface {@link LogAuditoriaService},
 * se encarga de gestionar la logica de negocio de {@link LogAuditoria}
 * @author Alex
 */
@Service
public class LogAuditoriaServiceImpl implements LogAuditoriaService {

    @Autowired
    private UsuarioServiceImpl usi;

    @Autowired
    private LogAuditoriaRepository lar;

    @Autowired
    private RolRepository rr;

    @Override
    public void registrarAccion(String tabla, Accion accion) {
        Usuario usuario = usi.getUsuarioAutenticadoId();

        LogAuditoria log = new LogAuditoria();
        log.setUsuario(usuario);
        log.setTablaAfectada(tabla);
        log.setAccion(accion);
        log.setFechaAccion(LocalDateTime.now());

        lar.save(log);

    }

    @Override
    public List<LogAuditoria> getLogsAuditorias() {
        List<LogAuditoria> logs = (List<LogAuditoria>) lar.findAll();

        List<Rol> roles = (List<Rol>) rr.findAll();

        List<Rol> rolesUser = roles.stream()
                .filter(rol -> rol.getNombreRol().equals("admin"))
                .collect(Collectors.toList());

        List<LogAuditoria> logsAuditoria = logs.stream()
                .filter(log -> log.getUsuario().getRoles().stream()
                        .anyMatch(rol -> rolesUser.contains(rol)))
                .collect(Collectors.toList());

        return logsAuditoria;
    }

}
