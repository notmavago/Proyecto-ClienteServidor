package modelo;

import java.io.Serializable;

/*
 * Clase Ejercicio
 *
 * Representa un ejercicio físico dentro del sistema.
 *
 * Un ejercicio puede:
 *  - Ser creado por el usuario
 *  - Ser predeterminado por el sistema
 *
 * Se mapea directamente con la tabla "ejercicio" en la base de datos.
 */
public class Ejercicio implements Serializable {

    // Identificador único en la base de datos.
    private int id;

    // Nombre del ejercicio.
    private String nombre;

    // Descripción opcional del ejercicio.
    private String descripcion;

    // Tipo o grupo muscular del ejercicio.
    private String tipo;

    // Indica si el ejercicio es predeterminado del sistema.
    // Si es true, no puede ser editado ni eliminado.
    private boolean esPredeterminado;

    /*
     * Constructor vacío.
     * Necesario para algunos frameworks y para flexibilidad.
     */
    public Ejercicio() {
    }

    /*
     * Constructor usado cuando se crea un ejercicio nuevo
     * antes de insertarlo en base de datos.
     * El ID aún no existe.
     */
    public Ejercicio(String nombre, String descripcion, String tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.esPredeterminado = false;
    }

    /*
     * Constructor completo.
     * Se usa cuando el ejercicio viene desde la base de datos.
     */
    public Ejercicio(int id,
                     String nombre,
                     String descripcion,
                     String tipo,
                     boolean esPredeterminado) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.esPredeterminado = esPredeterminado;
    }

    // Devuelve el identificador único del ejercicio.
    public int getId() {
        return id;
    }

    // Establece el identificador del ejercicio.
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el nombre del ejercicio.
    public String getNombre() {
        return nombre;
    }

    // Establece el nombre del ejercicio.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve la descripción del ejercicio.
    public String getDescripcion() {
        return descripcion;
    }

    // Establece la descripción del ejercicio.
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Devuelve el tipo o grupo muscular.
    public String getTipo() {
        return tipo;
    }

    // Establece el tipo o grupo muscular.
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Indica si el ejercicio es predeterminado.
    public boolean isEsPredeterminado() {
        return esPredeterminado;
    }

    // Define si el ejercicio es predeterminado.
    public void setEsPredeterminado(boolean esPredeterminado) {
        this.esPredeterminado = esPredeterminado;
    }

    /*
     * Representación textual del ejercicio.
     * Se usa en los JList para mostrar el nombre.
     */
    @Override
    public String toString() {
        return nombre;
    }
}