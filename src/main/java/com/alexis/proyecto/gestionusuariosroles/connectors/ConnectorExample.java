package com.alexis.proyecto.gestionusuariosroles.connectors;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectorExample {

    private static String url = "jdbc:mariadb://example.com:3306/database_name";
    private static String user = "example_user";
    private static String pass = "example_password";
    private static Connection conn = null;

    private ConnectorExample() {
    }

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e);
        }
        return conn;
    }

}
