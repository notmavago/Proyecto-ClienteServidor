package app;

import modelo.*;
import vista.VentanaLogin;

//Clase principal que inicia el sistema.
public class Main {

    public static void main(String[] args) {

        //Si el datos.dat existe, se recuperan usuarios y rutinas, si no existe o ocurre error, se crea un sistema nuevo.
        SistemaFidness sistema = SistemaFidness.cargarSistema("datos.dat");
        
        //Se inicializan datos básicos de prueba.
        if (sistema.getListaEjercicios().isEmpty()) {

            //Se crea un usuario por defecto.
            Usuario usuario = new Usuario("admin", "123");
            sistema.registrarUsuario(usuario);

            //Se agregan ejercicios iniciales.
            sistema.agregarEjercicio(new EjercicioPierna(1, "Sentadilla", "Fortalece piernas"));
            sistema.agregarEjercicio(new EjercicioBrazo(2, "Curl Bíceps", "Fortalece brazos"));
        }

        //Se lanza la ventana de login, pasando la instancia única del sistema.
        new VentanaLogin(sistema).setVisible(true);

            //Este hilo se ejecuta automáticamente cuando la aplicación se cierra.
            //Su función es guardar el estado actual del sistema en el archivo "datos.dat".
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                sistema.guardarSistema("datos.dat");
            }));
    }
}