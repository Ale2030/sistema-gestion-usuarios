package com.alexis.proyecto.gestionusuariosroles.services;

import java.util.List;

import com.alexis.proyecto.gestionusuariosroles.domain.LogAuditoria;
import com.alexis.proyecto.gestionusuariosroles.enums.Accion;

/**
 * Interfaz para el servicio de gestión de auditorías.
 * @author Alex 
 * */
public interface LogAuditoriaService {

    /**
     * @return Metodo que retorna una lista de logAuditoria
     */
    List<LogAuditoria> getLogsAuditorias();

    /**
     * Metodo para registrar la accion del usuario.
     * @param tabla tabla el la que se realizo el cambio.
     * @param accion que accion realizo.
     */
    void registrarAccion(String tabla, Accion accion);
}
