package modelo;

import java.io.Serializable;

//Implementa Serializable para permitir persistencia futura.
public abstract class Ejercicio implements Serializable {

    //Identificador único del ejercicio
    protected int id;

    //Nombre del ejercicio
    protected String nombre;

    //Descripción general del ejercicio
    protected String descripcion;

    /**
    Constructor base para cualquier tipo de ejercicio.
    
    @param id: Identificador único
    @param nombre: Nombre del ejercicio
    @param descripcion: Descripción general
    */
    public Ejercicio(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
    Método abstracto que será implementado por cada tipo específico de ejercicio. 
    
    @return Información detallada del ejercicio.
    */
    public abstract String mostrarDetalle();
    
//Obtiene el ID del ejercicio.
    public int getId() {
        return id;
    }

    //Obtiene el nombre del ejercicio.
    public String getNombre() {
        return nombre;
    }

    //Obtiene la descripción del ejercicio.
    public String getDescripcion() {
        return descripcion;
    }
    
    //Representación en texto del ejercicio.
    @Override
    public String toString() {
        return nombre;
    }
}
