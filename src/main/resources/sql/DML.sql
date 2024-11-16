-- Active: 1714186397700@@127.0.0.1@3306@usuarios_y_roles_db
INSERT INTO usuarios (nombre, email, password, rol, activo)
VALUES
    ('Juan Pérez', 'juan.perez@example.com', 'password1223', 'admin', TRUE),
    ('Ana García', 'ana.garcia@example.com', 'password1243', 'user', TRUE),
    ('Luis Martínez', 'luis.martinez@example.com', 'password1523', 'user', TRUE),
    ('María López', 'maria.lopez@example.com', 'password1273', 'user', TRUE),
    ('Carlos Sánchez', 'carlos.sanchez@example.com', 'password1823', 'admin', TRUE),
    ('Laura Fernández', 'laura.fernandez@example.com', 'password123', 'user', TRUE),
    ('Pedro Díaz', 'pedro.diaz@example.com', 'password1023', 'user', TRUE),
    ('Elena Rodríguez', 'elena.rodriguez@example.com', 'password1823', 'admin', TRUE),
    ('José González', 'jose.gonzalez@example.com', 'password14523', 'user', TRUE),
    ('Sandra Martínez', 'sandra.martinez@example.com', 'password13523', 'user', TRUE);

INSERT INTO roles (nombre_rol)
VALUES
    ('admin'),
    ('user');

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
    (10, 'CREATE', 'roles');


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

