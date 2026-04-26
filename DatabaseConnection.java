package infraestructura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Clase DatabaseConnection
 *
 * Responsable de gestionar la conexión con la base de datos MySQL.
 *
 * Esta clase pertenece a la capa de persistencia.
 * Centraliza la configuración de acceso a la base de datos
 * para evitar duplicación de código en los DAO.
 *
 * No contiene lógica de negocio.
 * No contiene lógica de interfaz.
 * Solo proporciona conexiones JDBC.
 */
public class DatabaseConnection {

    // URL de conexión JDBC.
    // Especifica:
    //  - Motor (mysql)
    //  - Host (localhost)
    //  - Puerto (3306)
    //  - Base de datos (fidness)
    private static final String URL =
            "jdbc:mysql://localhost:3306/fidness";

    // Usuario con permisos para acceder a la base de datos.
    private static final String USER = "root";

    // Contraseña del usuario de la base de datos.
    private static final String PASSWORD = "1234";

    // Método estático que devuelve una nueva conexión activa.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}