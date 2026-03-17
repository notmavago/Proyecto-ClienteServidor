package vista;

import modelo.Ejercicio;
import modelo.SistemaFidness;

import javax.swing.*;
import java.awt.*;

//Ventana que muestra la lista de ejercicios disponibles.
public class VentanaEjercicios extends JFrame {

    private SistemaFidness sistema;
    private JList<Ejercicio> lista;

    public VentanaEjercicios(SistemaFidness sistema) {

        this.sistema = sistema;

        setTitle("Lista de Ejercicios");
        setSize(400, 300);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    //Método encargado de inicializar y organizar todos los componentes gráficos de la ventana.
    private void inicializarComponentes() {

        //Modelo de datos que almacenará los objetos Ejercicio que serán mostrados en el componente JList.
        //DefaultListModel permite manejar dinámicamente los elementos que contiene la lista.
        DefaultListModel<Ejercicio> modeloLista = new DefaultListModel<>();

        for (Ejercicio e : sistema.getListaEjercicios()) {
            modeloLista.addElement(e);
        }

        //Se crea el componente visual JList y se le asigna el modelo previamente construido.
        lista = new JList<>(modeloLista);

        //Botón que permitirá visualizar el detalle del ejercicio seleccionado.
        JButton btnDetalle = new JButton("Ver Detalle");

        //Este bloque define qué ocurre cuando el usuario presiona el botón.
        btnDetalle.addActionListener(e -> {
            Ejercicio seleccionado = lista.getSelectedValue();
            if (seleccionado != null) {
                JOptionPane.showMessageDialog(this,
                        seleccionado.mostrarDetalle());
            }
        });

        //Se agrega la lista dentro de un JScrollPane.
        add(new JScrollPane(lista), BorderLayout.CENTER);
        add(btnDetalle, BorderLayout.SOUTH);
    }
}