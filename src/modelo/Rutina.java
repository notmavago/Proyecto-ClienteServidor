package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Representa una rutina creada por un usuario.
public class Rutina implements Serializable {

    private int id;
    private String nombre;
    private Date fechaCreacion;
    private List<Ejercicio> listaEjercicios;

    //Constructor de la rutina.
    public Rutina(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = new Date();
        this.listaEjercicios = new ArrayList<>();
    }

    //Agrega un ejercicio a la rutina.
    public void agregarEjercicio(Ejercicio ejercicio) {
        listaEjercicios.add(ejercicio);
    }

    //Elimina un ejercicio de la rutina.
    public void eliminarEjercicio(Ejercicio ejercicio) {
        listaEjercicios.remove(ejercicio);
    }

    //Muestra la rutina completa en formato texto.
    public String mostrarRutina() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rutina: ").append(nombre).append("\n");

        for (Ejercicio e : listaEjercicios) {
            sb.append("- ").append(e.mostrarDetalle()).append("\n");
        }

        return sb.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }
}