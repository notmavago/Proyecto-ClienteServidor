package vista;

import modelo.SistemaFidness;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

//Ventana principal del sistema.
public class VentanaMenu extends JFrame {

    private SistemaFidness sistema;
    private Usuario usuario;

    //Constructor del menú principal.
    public VentanaMenu(SistemaFidness sistema, Usuario usuario) {

        this.sistema = sistema;
        this.usuario = usuario;

        setTitle("Menú Principal - Fidness");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inicializarComponentes();
    }

    //Método encargado de inicializar y organizar los componentes gráficos del menú principal.
    private void inicializarComponentes() {

        JPanel panel = new JPanel();
        //Se define un GridLayout de 3 filas y 1 columna, con separación horizontal y vertical de 10 píxeles.
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnEjercicios = new JButton("Ver Ejercicios");
        JButton btnCrearRutina = new JButton("Crear Rutina");
        JButton btnVerRutinas = new JButton("Ver Mis Rutinas");

        panel.add(btnEjercicios);
        panel.add(btnCrearRutina);
        panel.add(btnVerRutinas);

        add(panel);

        //Eventos
        btnEjercicios.addActionListener(e -> 
            new VentanaEjercicios(sistema).setVisible(true)
        );

        btnCrearRutina.addActionListener(e -> 
            new VentanaCrearRutina(sistema, usuario).setVisible(true)
        );

        btnVerRutinas.addActionListener(e -> 
            JOptionPane.showMessageDialog(this,
                    usuario.getListaRutinas().toString())
        );
    }
}