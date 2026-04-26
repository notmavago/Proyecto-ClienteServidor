package app;

import modelo.Ejercicio;
import modelo.Rutina;
import infraestructura.EjercicioDAO;
import infraestructura.RutinaDAO;

import java.util.List;

/*
 * Clase InicializadorDatos
 *
 * Clase utilitaria encargada de poblar la base de datos
 * con datos predeterminados únicamente si aún no existen.
 *
 * Su propósito es garantizar que el sistema siempre tenga
 * ejercicios y rutinas base disponibles al iniciar por
 * primera vez, sin duplicar información.
 */
public class InicializadorDatos {

    /*
     * Punto de entrada del inicializador.
     * Ejecuta la carga condicional de datos base.
     */
    public static void inicializar() {

        insertarEjerciciosSiNoExisten();
        insertarRutinasRecomendadasSiNoExisten();
    }

    /*
     * Inserta ejercicios predeterminados si la tabla está vacía.
     *
     * Se consulta primero la base de datos.
     * Si ya existen registros, no se hace nada.
     * Esto evita duplicaciones en reinicios posteriores.
     */
    private static void insertarEjerciciosSiNoExisten() {

        EjercicioDAO ejercicioDAO = new EjercicioDAO();

        // Se obtienen todos los ejercicios actuales
        List<Ejercicio> existentes = ejercicioDAO.obtenerTodos();

        // Si ya hay ejercicios registrados, se cancela la inserción
        if (!existentes.isEmpty()) {
            return;
        }

        // Inserción de ejercicios base del sistema
        ejercicioDAO.insertarEjercicio(
                new Ejercicio("Sentadilla", "Fortalece piernas", "Pierna"));

        ejercicioDAO.insertarEjercicio(
                new Ejercicio("Curl Bíceps", "Fortalece brazos", "Brazo"));

        ejercicioDAO.insertarEjercicio(
                new Ejercicio("Lagartijas", "Fortalece pecho", "Pecho"));

        ejercicioDAO.insertarEjercicio(
                new Ejercicio("Dominadas", "Fortalece espalda", "Espalda"));
    }

    /*
     * Inserta rutinas recomendadas si no existen previamente.
     *
     * Las rutinas recomendadas se identifican porque:
     *  - Son marcadas como recomendadas (true)
     *  - No pertenecen a un usuario específico
     *
     * Solo se insertan si la base aún no contiene rutinas predeterminadas.
     */
    private static void insertarRutinasRecomendadasSiNoExisten() {

        RutinaDAO rutinaDAO = new RutinaDAO();

        // Se consultan únicamente las rutinas recomendadas
        List<Rutina> recomendadas = rutinaDAO.obtenerRutinasRecomendadas();

        // Si ya existen rutinas recomendadas, se detiene el proceso
        if (!recomendadas.isEmpty()) {
            return;
        }

        // Rutina general de cuerpo completo
        Rutina fullBody =
                new Rutina("Full Body Principiante", true);

        rutinaDAO.insertarRutina(fullBody, 0);

        // Rutina intermedia enfocada en espalda y abdomen
        Rutina espaldaAbdomen =
                new Rutina("Espalda y Abdomen Intermedio", true);

        rutinaDAO.insertarRutina(espaldaAbdomen, 0);
    }
}