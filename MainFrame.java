package app;

import vista.PanelLogin;

import javax.swing.*;
import java.awt.*;

/*
 * Clase MainFrame
 *
 * Ventana principal del sistema.
 * Actúa como contenedor único de toda la aplicación.
 *
 * Utiliza CardLayout para permitir navegación fluida
 * entre pantallas sin crear nuevas ventanas.
 *
 * Centraliza la gestión de vistas.
 */
public class MainFrame extends JFrame {

    // Administrador de vistas que permite cambiar entre paneles
    private CardLayout cardLayout;

    // Contenedor principal donde se agregan todos los paneles
    private JPanel contenedor;

    /*
     * Constructor principal.
     *
     * Configura la ventana base y prepara
     * la infraestructura de navegación.
     */
    public MainFrame() {

        setTitle("Sistema Fidness");
        setSize(450, 300);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Se inicializa el sistema de navegación
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        add(contenedor);

        // Se carga la primera pantalla del sistema
        inicializarLogin();
    }

    /*
     * Registra un nuevo panel dentro del CardLayout.
     *
     * Cada panel se identifica mediante un nombre único
     * que luego se usa para navegar entre pantallas.
     */
    public void agregarPanel(JPanel panel, String nombre) {
        contenedor.add(panel, nombre);
    }

    /*
     * Muestra un panel previamente registrado.
     *
     * Permite cambiar de pantalla sin cerrar la ventana,
     * manteniendo una experiencia de navegación fluida.
     */
    public void mostrarPanel(String nombre) {
        cardLayout.show(contenedor, nombre);
    }

    /*
     * Inicializa la pantalla de login al arrancar el sistema.
     *
     * Se registra el panel en el CardLayout
     * y se muestra como vista inicial.
     */
    private void inicializarLogin() {

        PanelLogin login = new PanelLogin(this);

        agregarPanel(login, "LOGIN");
        mostrarPanel("LOGIN");
    }
}