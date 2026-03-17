package modelo;

//Hereda de la clase abstracta Ejercicio.
public class EjercicioPierna extends Ejercicio {

    //Constructor específico para ejercicios de pierna.
    public EjercicioPierna(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    //Implementación polimórfica del método abstracto.
    @Override
    public String mostrarDetalle() {
        return "Ejercicio de Pierna: " + nombre + "\nDescripción: " + descripcion;
    }
}
