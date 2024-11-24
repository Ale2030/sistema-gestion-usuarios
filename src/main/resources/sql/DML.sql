-- Active: 1714186397700@@127.0.0.1@3306@usuarios_y_roles_db
INSERT INTO usuarios (nombre, email, password, activo)
VALUES
    ('Juan Pérez', 'juan.perez@example.com', 'password1223', TRUE),
    ('Ana García', 'ana.garcia@example.com', 'password1243', TRUE),
    ('Luis Martínez', 'luis.martinez@example.com', 'password1523', TRUE),
    ('María López', 'maria.lopez@example.com', 'password1273', TRUE),
    ('Carlos Sánchez', 'carlos.sanchez@example.com', 'password1823', TRUE),
    ('Laura Fernández', 'laura.fernandez@example.com', 'password123', TRUE),
    ('Pedro Díaz', 'pedro.diaz@example.com', 'password1023', TRUE),
    ('Elena Rodríguez', 'elena.rodriguez@example.com', 'password1823', TRUE),
    ('José González', 'jose.gonzalez@example.com', 'password14523', TRUE),
    ('Sandra Martínez', 'sandra.martinez@example.com', 'password13523', TRUE);

-- Insertar roles
INSERT INTO roles (nombre_rol)
VALUES
    ('admin'),
    ('user');

-- Insertar permisos
INSERT INTO permisos (id_rol, accion)
VALUES
    (1, 'CREATE'),
    (1, 'UPDATE'),
    (1, 'DELETE'),
    (1, 'READ'),
    (2, 'READ'),
    (2, 'UPDATE'),
    (2, 'CREATE'),
    (2, 'DELETE');

-- Insertar usuario_rol (relación entre usuarios y roles)
INSERT INTO usuarios_roles (id_usuario, id_rol)
VALUES
    (1, 1),
    (2, 2),
    (3, 2),  
    (4, 2),
    (5, 1),
    (6, 2), 
    (7, 2),
    (8, 1), 
    (9, 2),  
    (10, 2); 

-- Insertar logs de auditoría
INSERT INTO logs_auditoria (id_usuario, accion, tabla_afectada)
VALUES
    (1, 'CREATE', 'usuarios'),
    (2, 'UPDATE', 'usuarios'),
    (3, 'DELETE', 'usuarios'),
    (4, 'CREATE', 'roles'),
    (5, 'UPDATE', 'roles'),
    (6, 'DELETE', 'roles'),
    (7, 'CREATE', 'usuarios'),
    (8, 'UPDATE', 'usuarios'),
    (9, 'DELETE', 'usuarios'),
    (10, 'CREATE', 'usuarios'),
    (1, 'UPDATE', 'usuarios'),
    (5, 'DELETE', 'roles'),
    (8, 'READ', 'usuarios'),
    (9, 'UPDATE', 'usuarios');
