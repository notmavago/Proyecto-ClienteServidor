package modelo;

/*
 * Rutina
 *
 * Entidad del dominio que representa una rutina de entrenamiento.
 *
 * Una rutina puede ser:
 *  - Recomendada por el sistema (no pertenece a un usuario específico)
 *  - Creada por un usuario
 *
 * La relación con ejercicios se gestiona mediante la tabla intermedia
 * rutina_ejercicio en la capa de persistencia.
 */
public class Rutina {

    // Identificador único generado por la base de datos
    private int id;

    // Nombre descriptivo de la rutina
    private String nombre;

    // Indica si la rutina es predeterminada del sistema
    private boolean esRecomendada;

    // Identificador del usuario propietario (0 o NULL si es recomendada)
    private int usuarioId;

    /*
     * Constructor vacío.
     * Se mantiene por compatibilidad con algunos procesos
     * que requieren instanciación sin parámetros.
     */
    public Rutina() {
    }

    /*
     * Constructor completo.
     * Se utiliza al reconstruir la entidad desde la base de datos.
     */
    public Rutina(int id, String nombre, boolean esRecomendada, int usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.esRecomendada = esRecomendada;
        this.usuarioId = usuarioId;
    }

    /*
     * Constructor utilizado al crear una nueva rutina
     * antes de persistirla en la base de datos.
     */
    public Rutina(String nombre, boolean esRecomendada) {
        this.nombre = nombre;
        this.esRecomendada = esRecomendada;
    }

    // Devuelve el identificador único de la rutina
    public int getId() {
        return id;
    }

    // Permite establecer el ID (normalmente asignado por la BD)
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el nombre de la rutina
    public String getNombre() {
        return nombre;
    }

    // Modifica el nombre de la rutina
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Indica si la rutina es recomendada por el sistema
    public boolean isEsRecomendada() {
        return esRecomendada;
    }

    // Define si la rutina es recomendada
    public void setEsRecomendada(boolean esRecomendada) {
        this.esRecomendada = esRecomendada;
    }

    // Devuelve el ID del usuario propietario
    public int getUsuarioId() {
        return usuarioId;
    }

    // Establece el ID del usuario propietario
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    /*
     * Representación textual utilizada en componentes gráficos
     * como JList. Añade una etiqueta si es rutina recomendada.
     */
    @Override
    public String toString() {
        if (esRecomendada) {
            return nombre + " (Recomendada)";
        }
        return nombre;
    }
}