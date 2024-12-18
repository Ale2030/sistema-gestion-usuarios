-- Esta consulta selecciona todos los usuarios activos.
SELECT id_usuario, nombre, email, activo
FROM usuarios
WHERE activo = TRUE;

-- Esta consulta selecciona todos los roles disponibles en la base de datos.
SELECT id_rol, nombre_rol
FROM roles;



-- Esta consulta selecciona los logs de auditoría junto con el nombre del usuario que realizó la acción.
-- Utiliza un `JOIN` entre `logs_auditoria` y `usuarios` para obtener el nombre del usuario.
SELECT la.id_log, u.nombre AS usuario, la.accion, la.tabla_afectada, la.fecha_accion
FROM logs_auditoria la
JOIN usuarios u ON la.id_usuario = u.id_usuario;


-- Esta consulta selecciona los logs de auditoría por usuario y acción.
-- Utiliza un `JOIN` entre `logs_auditoria` y `usuarios` para obtener el nombre del usuario que realizó la acción.
SELECT la.id_log, u.nombre AS usuario, la.accion, la.tabla_afectada, la.fecha_accion
FROM logs_auditoria la
JOIN usuarios u ON la.id_usuario = u.id_usuario
WHERE la.accion = 'UPDATE';

-- Esta consulta selecciona los usuarios junto con sus roles, excluyendo a los usuarios inactivos.
-- Utiliza `JOIN` entre `usuarios`, `usuarios_roles`, y `roles`.
SELECT u.id_usuario, u.nombre, r.nombre_rol
FROM usuarios u
JOIN usuarios_roles ur ON u.id_usuario = ur.id_usuario
JOIN roles r ON ur.id_rol = r.id_rol
WHERE u.activo = TRUE;

-- Esta consulta cuenta la cantidad de usuarios asignados a cada rol.
-- Utiliza `JOIN` entre las tablas `usuarios`, `usuarios_roles`, y `roles` para agrupar por `rol` y contar los usuarios.
SELECT r.nombre_rol, COUNT(u.id_usuario) AS cantidad_usuarios
FROM roles r
JOIN usuarios_roles ur ON r.id_rol = ur.id_rol
JOIN usuarios u ON ur.id_usuario = u.id_usuario
GROUP BY r.id_rol;

-- Esta consulta selecciona los logs de auditoría de un usuario específico usando su `id_usuario`.
-- Se usa un `JOIN` entre las tablas `logs_auditoria` y `usuarios` para obtener el nombre del usuario y filtrar por un `id_usuario` específico.
SELECT la.id_log, u.nombre AS usuario, la.accion, la.tabla_afectada, la.fecha_accion
FROM logs_auditoria la
JOIN usuarios u ON la.id_usuario = u.id_usuario
WHERE u.id_usuario = 1;
