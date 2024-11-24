-- Active: 1714186397700@@127.0.0.1@3306@usuarios_y_roles_db
DROP DATABASE IF EXISTS usuarios_y_roles_db;

CREATE DATABASE usuarios_y_roles_db;

USE usuarios_y_roles_db;

DROP TABLE IF EXISTS usuarios;

DROP TABLE IF EXISTS roles;

DROP TABLE IF EXISTS permisos;

DROP TABLE IF EXISTS logs_auditoria;

DROP TABLE IF EXISTS usuarios_roles;

CREATE TABLE usuarios(
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    email VARCHAR(60) NOT NULL,
    password VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE usuarios
    ADD CONSTRAINT CK_Usuarios_Nombre_Length
    CHECK(LENGTH(nombre)>=3);

ALTER TABLE usuarios ADD CONSTRAINT unique_email UNIQUE (email);

CREATE TABLE roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(20) NOT NULL 
);

ALTER TABLE roles ADD CONSTRAINT unique_nombre_rol UNIQUE (nombre_rol);

CREATE TABLE usuarios_roles (
    id_usuario INT NOT NULL,
    id_rol INT NOT NULL,
    PRIMARY KEY (id_usuario, id_rol)  
);

ALTER TABLE usuarios_roles
    ADD CONSTRAINT fk_usuario_rol FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE;

ALTER TABLE usuarios_roles
    ADD CONSTRAINT fk_rol FOREIGN KEY (id_rol) REFERENCES roles(id_rol) ON DELETE CASCADE;

CREATE TABLE permisos(
    id_permiso INT AUTO_INCREMENT PRIMARY KEY,
    id_rol INT NOT NULL,
    accion ENUM('CREATE', 'UPDATE', 'DELETE', 'READ') NOT NULL
);
ALTER TABLE permisos
    ADD CONSTRAINT fk_permisos_rol FOREIGN KEY (id_rol) REFERENCES roles(id_rol) ON DELETE CASCADE;
CREATE TABLE logs_auditoria(
    id_log INT AUTO_INCREMENT PRIMARY KEY ,
    id_usuario INT NOT NULL,
    accion ENUM('CREATE','UPDATE','DELETE') NOT NULL,
    tabla_afectada VARCHAR(100) NOT NULL,
    fecha_accion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE logs_auditoria
    ADD CONSTRAINT fk_usuario_logs
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
    ON DELETE CASCADE;
    