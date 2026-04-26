package infraestructura;

import modelo.Usuario;

import java.sql.*;

/*
 * UsuarioDAO
 *
 * DAO responsable de todas las operaciones de persistencia
 * relacionadas con la entidad Usuario.
 *
 * Su función es:
 *  - Ejecutar consultas SQL
 *  - Transformar resultados en objetos del modelo
 *
 * No contiene lógica de negocio ni lógica de interfaz.
 */
public class UsuarioDAO {

    /*
     * Inserta un nuevo usuario en la base de datos.
     *
     * Recibe un objeto Usuario con username y password.
     * No retorna el ID generado en este diseño.
     */
    public void registrar(Usuario usuario) {

        String sql =
                "INSERT INTO usuario (username, password) VALUES (?, ?)";

        /*
         * try-with-resources garantiza el cierre automático
         * de Connection y PreparedStatement, evitando fugas
         * de recursos.
         */
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Valida credenciales contra la base de datos.
     *
     * Si existe coincidencia exacta de username y password,
     * reconstruye y devuelve el objeto Usuario.
     *
     * Devuelve null si no hay coincidencia.
     */
    public Usuario autenticar(String username, String password) {

        String sql =
                "SELECT * FROM usuario WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            /*
             * Si se obtiene al menos una fila,
             * las credenciales son válidas.
             */
            if (rs.next()) {

                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}