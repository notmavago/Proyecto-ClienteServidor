package modelo;

import java.util.ArrayList;
import java.util.List;

/*
 * Usuario
 *
 * Entidad del dominio que representa a un usuario autenticable
 * dentro del sistema.
 *
 * Su responsabilidad es modelar el estado del usuario:
 *  - Identidad
 *  - Credenciales
 *  - Relación lógica con sus rutinas
 *
 * No contiene lógica de persistencia ni lógica de interfaz.
 */
public class Usuario {

    // Identificador único generado por la base de datos
    private int id;

    // Nombre utilizado para autenticación
    private String username;

    // Contraseña asociada al usuario
    // Actualmente se maneja en texto plano (mejorable con hashing)
    private String password;

    // Colección de rutinas asociadas al usuario en memoria
    // La relación persistente se gestiona mediante la base de datos
    private List<Rutina> rutinas;

    /*
     * Constructor completo.
     *
     * Se utiliza cuando el usuario es reconstruido
     * desde la base de datos con su ID ya asignado.
     */
    public Usuario(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rutinas = new ArrayList<>();
    }

    /*
     * Constructor utilizado al crear un nuevo usuario
     * antes de ser persistido.
     *
     * El ID será generado posteriormente por la base de datos.
     */
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        this.rutinas = new ArrayList<>();
    }

    // Devuelve el identificador único del usuario
    public int getId() {
        return id;
    }

    // Devuelve el nombre de usuario
    public String getUsername() {
        return username;
    }

    // Devuelve la contraseña almacenada
    public String getPassword() {
        return password;
    }

    /*
     * Agrega una rutina a la colección en memoria.
     *
     * No realiza inserciones en base de datos.
     * Solo mantiene coherencia del objeto durante la ejecución.
     */
    public void agregarRutina(Rutina rutina) {
        rutinas.add(rutina);
    }

    /*
     * Devuelve las rutinas actualmente asociadas
     * al objeto en memoria.
     */
    public List<Rutina> getRutinas() {
        return rutinas;
    }

    /*
     * Representación textual del usuario.
     *
     * Se utiliza en componentes gráficos cuando
     * el objeto se muestra directamente.
     */
    @Override
    public String toString() {
        return username;
    }
}