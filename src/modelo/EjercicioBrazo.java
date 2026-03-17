package modelo;

//Representa un ejercicio específico del tipo Brazo.
public class EjercicioBrazo extends Ejercicio {

    public EjercicioBrazo(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    @Override
    public String mostrarDetalle() {
        return "Ejercicio de Brazo: " + nombre + "\nDescripción: " + descripcion;
    }
}