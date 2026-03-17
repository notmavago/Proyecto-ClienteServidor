package vista;

import modelo.*;

import javax.swing.*;
import java.awt.*;

//Ventana encargada de permitir al usuario crear una nueva rutina personalizada.
public class VentanaCrearRutina extends JFrame {

    //Referencia al sistema central, permite acceder a ejercicios disponibles.
    private SistemaFidness sistema;

    //Usuario que está actualmente autenticado.
    private Usuario usuario;

    //Campo de texto donde el usuario ingresa el nombre de la rutina.
    private JTextField txtNombreRutina;

    //Lista visual que muestra los ejercicios disponibles.
    private JList<Ejercicio> listaEjercicios;

    /**
    Constructor de la ventana.
    
    @param sistema Instancia del sistema principal.
    @param usuario Usuario autenticado que creará la rutina.
    */
    public VentanaCrearRutina(SistemaFidness sistema, Usuario usuario) {

        this.sistema = sistema;
        this.usuario = usuario;

        setTitle("Crear Rutina");
        setSize(400, 350);
        setLocationRelativeTo(null);

        //Inicializa los componentes gráficos.
        inicializarComponentes();
    }

    //Método encargado de construir y organizar todos los componentes visuales de la ventana.
    private void inicializarComponentes() {

        //Se define el layout principal como BorderLayout.
        setLayout(new BorderLayout());

        //Campo de texto para el nombre de la rutina.
        txtNombreRutina = new JTextField();

        //Modelo que almacenará los ejercicios que serán mostrados en la lista.
        DefaultListModel<Ejercicio> modeloLista = new DefaultListModel<>();

        //Se cargan todos los ejercicios disponibles en el sistema dentro del modelo.
        for (Ejercicio e : sistema.getListaEjercicios()) {
            modeloLista.addElement(e);
        }

        //Se crea la lista visual con el modelo cargado.
        listaEjercicios = new JList<>(modeloLista);
        
        //Se permite seleccionar múltiples ejercicios para construir la rutina personalizada.
        listaEjercicios.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        //Botón encargado de guardar la rutina creada.
        JButton btnGuardar = new JButton("Guardar Rutina");

        //Evento que se ejecuta cuando el usuario presiona el botón "Guardar Rutina".
        btnGuardar.addActionListener(e -> guardarRutina());

        //Se agregan los componentes al layout.
        add(new JLabel("Nombre de la rutina:"), BorderLayout.NORTH);
        add(txtNombreRutina, BorderLayout.BEFORE_FIRST_LINE);
        add(new JScrollPane(listaEjercicios), BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
    }

    //Método que construye y guarda la rutina seleccionada por el usuario.
    private void guardarRutina() {

        //Se obtiene el nombre ingresado.
        String nombre = txtNombreRutina.getText();

        //Validación básica para evitar rutinas sin nombre.
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar un nombre",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Se crea una nueva instancia de Rutina.
        //El ID se genera dinámicamente según la cantidad de rutinas del usuario.
        Rutina rutina = new Rutina(
                usuario.getListaRutinas().size() + 1, nombre);

        //Se recorren los ejercicios seleccionados en la lista y se agregan a la rutina.
        for (Ejercicio e : listaEjercicios.getSelectedValuesList()) {
            rutina.agregarEjercicio(e);
        }

        //La rutina creada se asocia al usuario actual.
        usuario.crearRutina(rutina);

        //Confirmación visual para el usuario.
        JOptionPane.showMessageDialog(this,
                "Rutina creada exitosamente");

        //Se cierra la ventana después de guardar.
        dispose();
    }
}