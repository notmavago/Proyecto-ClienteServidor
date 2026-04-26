package infraestructura;

import modelo.Ejercicio;
import modelo.Rutina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * RutinaDAO
 *
 * DAO responsable de la persistencia de la entidad Rutina
 * y de su relación con Ejercicio mediante la tabla intermedia
 * rutina_ejercicio.
 *
 * Aísla completamente la lógica SQL del resto del sistema.
 */
public class RutinaDAO {

    /*
     * Obtiene todas las rutinas visibles para un usuario.
     *
     * Incluye:
     *  - Rutinas creadas por el usuario
     *  - Rutinas recomendadas del sistema (usuario_id IS NULL)
     *
     * Esto permite que el usuario vea tanto sus rutinas
     * como las predeterminadas.
     */
    public List<Rutina> obtenerRutinasPorUsuario(int usuarioId) {

        List<Rutina> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM rutina " +
                "WHERE usuario_id = ? OR usuario_id IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                lista.add(new Rutina(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getBoolean("es_recomendada"),
                        rs.getInt("usuario_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /*
     * Recupera únicamente las rutinas recomendadas del sistema.
     *
     * Se identifican porque no pertenecen a ningún usuario.
     */
    public List<Rutina> obtenerRutinasRecomendadas() {

        List<Rutina> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM rutina WHERE usuario_id IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(new Rutina(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getBoolean("es_recomendada"),
                        rs.getInt("usuario_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /*
     * Inserta una nueva rutina en la base de datos.
     *
     * Devuelve el ID generado automáticamente por la base de datos.
     * Esto es necesario para posteriormente insertar sus ejercicios
     * en la tabla intermedia.
     */
    public int insertarRutina(Rutina rutina, int usuarioId) {

        String sql =
                "INSERT INTO rutina (nombre, es_recomendada, usuario_id) " +
                "VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, rutina.getNombre());
            stmt.setBoolean(2, rutina.isEsRecomendada());
            stmt.setInt(3, usuarioId);

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();

            if (keys.next()) {
                return keys.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /*
     * Crea la relación entre una rutina y un ejercicio.
     *
     * Representa la asociación muchos-a-muchos
     * gestionada por la tabla rutina_ejercicio.
     */
    public void insertarEjercicioEnRutina(int rutinaId, int ejercicioId) {

        String sql =
                "INSERT INTO rutina_ejercicio (rutina_id, ejercicio_id) " +
                "VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rutinaId);
            stmt.setInt(2, ejercicioId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Obtiene todos los ejercicios asociados a una rutina.
     *
     * Utiliza JOIN para reconstruir la relación
     * entre rutina y ejercicio.
     */
    public List<Ejercicio> obtenerEjerciciosDeRutina(int rutinaId) {

        List<Ejercicio> lista = new ArrayList<>();

        String sql =
                "SELECT e.* FROM ejercicio e " +
                "JOIN rutina_ejercicio re ON e.id = re.ejercicio_id " +
                "WHERE re.rutina_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rutinaId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                lista.add(new Ejercicio(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("tipo"),
                        rs.getBoolean("es_predeterminado")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /*
     * Actualiza únicamente el nombre de una rutina.
     *
     * No modifica relaciones ni estado recomendado.
     */
    public void actualizarNombreRutina(int rutinaId, String nuevoNombre) {

        String sql =
                "UPDATE rutina SET nombre = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoNombre);
            stmt.setInt(2, rutinaId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Elimina todas las relaciones de una rutina
     * en la tabla intermedia.
     *
     * Se utiliza antes de reconstruir la lista
     * de ejercicios al editar una rutina.
     */
    public void eliminarRelacionesDeRutina(int rutinaId) {

        String sql =
                "DELETE FROM rutina_ejercicio WHERE rutina_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rutinaId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Elimina una rutina de la base de datos.
     *
     * Gracias a ON DELETE CASCADE, las relaciones
     * en rutina_ejercicio se eliminan automáticamente.
     */
    public void eliminarRutina(int rutinaId) {

        String sql =
                "DELETE FROM rutina WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rutinaId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}