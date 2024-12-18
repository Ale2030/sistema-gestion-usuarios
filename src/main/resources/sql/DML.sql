-- Active: 1714186397700@@127.0.0.1@3306@usuarios_y_roles_db
INSERT INTO usuarios (nombre, email, password, activo)
VALUES
    ('Juan Pérez', 'juan@example.com', '$2y$10$4MfLPH4318loXxFD/MTbKuaqcniOvzuxXMRWOj.jFh71b8JoKtdd.', TRUE),
    ('Ana García', 'anagarcia@example.com', '$2y$10$9AsBX9vV6SPQhtjm.13fr.82nWKAFyT2h8bg2k2kT63aM8Klys7TC', TRUE),
    ('Luis Martínez', 'luis.martinez@example.com', '$2y$10$QFafoilcopk8kJg7gdJ7Yu8ZOaayABGbv4zvSEn5TqhJ7L.d7O9Jy', TRUE),
    ('María López', 'maria.lopez@example.com', '$2y$10$b1rRzayKlVN9rIug7oBneuI0G.xDAgmuMSMr87egpMeyoY9F15sXa', TRUE),
    ('Carlos Sánchez', 'carlos.sanchez@example.com', '$2y$10$5854dqVhSU5AjCjAnTGXk.OsMSAriS0n5t/r.miOBzz1ty5A7u6ti', TRUE);
    
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
    (1, 'DELETE');

-- Insertar usuario_rol (relación entre usuarios y roles)
INSERT INTO usuarios_roles (id_usuario, id_rol)
VALUES
    (1, 1),
    (2, 2),
    (3, 2),  
    (4, 2),
    (5, 1);
   

-- Insertar logs de auditoría
INSERT INTO logs_auditoria (id_usuario, accion, tabla_afectada)
VALUES
    (1, 'CREATE', 'usuarios'),
    (5, 'UPDATE', 'usuarios');
