package infraestructura;

import modelo.Ejercicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * EjercicioDAO
 *
 * Objeto de acceso a datos (DAO) responsable de gestionar
 * todas las operaciones de persistencia relacionadas con
 * la entidad Ejercicio.
 *
 * Forma parte de la capa de persistencia dentro del patrón MVC.
 * Su responsabilidad es aislar la lógica SQL del resto del sistema.
 */
public class EjercicioDAO {

    /*
     * Inserta un nuevo ejercicio en la base de datos.
     *
     * El campo es_predeterminado se establece en FALSE
     * porque los ejercicios creados desde la interfaz
     * pertenecen al usuario y no al sistema.
     */
    public void insertarEjercicio(Ejercicio ejercicio) {

        String sql = "INSERT INTO ejercicio (nombre, descripcion, tipo, es_predeterminado) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ejercicio.getNombre());
            stmt.setString(2, ejercicio.getDescripcion());
            stmt.setString(3, ejercicio.getTipo());
            stmt.setBoolean(4, false);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Recupera todos los ejercicios almacenados en la base de datos.
     *
     * Reconstruye cada fila como un objeto del dominio.
     */
    public List<Ejercicio> obtenerTodos() {

        List<Ejercicio> lista = new ArrayList<>();

        String sql = "SELECT * FROM ejercicio";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Ejercicio ejercicio = new Ejercicio(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("tipo"),
                        rs.getBoolean("es_predeterminado")
                );

                lista.add(ejercicio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /*
     * Obtiene un ejercicio específico por su identificador.
     *
     * Devuelve null si no existe.
     */
    public Ejercicio obtenerPorId(int id) {

        String sql = "SELECT * FROM ejercicio WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Ejercicio(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("tipo"),
                        rs.getBoolean("es_predeterminado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * Actualiza los datos de un ejercicio existente.
     *
     * Se protege contra modificaciones si el ejercicio
     * es predeterminado del sistema.
     */
    public void actualizarEjercicio(Ejercicio ejercicio) {

        // Protección de integridad para ejercicios base del sistema
        if (ejercicio.isEsPredeterminado()) {
            return;
        }

        String sql = "UPDATE ejercicio SET nombre = ?, descripcion = ?, tipo = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ejercicio.getNombre());
            stmt.setString(2, ejercicio.getDescripcion());
            stmt.setString(3, ejercicio.getTipo());
            stmt.setInt(4, ejercicio.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Elimina un ejercicio de la base de datos.
     *
     * No permite eliminar ejercicios predeterminados
     * para preservar datos base del sistema.
     */
    public void eliminarEjercicio(Ejercicio ejercicio) {

        // Protección adicional a nivel de lógica de negocio
        if (ejercicio.isEsPredeterminado()) {
            return;
        }

        String sql = "DELETE FROM ejercicio WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ejercicio.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}