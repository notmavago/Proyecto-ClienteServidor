package vista;

import modelo.SistemaFidness;
import modelo.Usuario;
import modelo.UsuarioInvalidoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Permite autenticar un usuario registrado.
public class VentanaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;

    private SistemaFidness sistema;

    /**
    Constructor de la ventana login.
     
    @param sistema Instancia central del sistema.
    */
    public VentanaLogin(SistemaFidness sistema) {
        this.sistema = sistema;

        setTitle("Login - Sistema Fidness");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inicializarComponentes();
    }


    //Inicializa y organiza los componentes gráficos.
    private void inicializarComponentes() {

        //Se establece un GridLayout de:
        //3 filas, 2 columnas, 10 píxeles de separación horizontal, 10 píxeles de separación vertical
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        //Etiqueta para el usuario.
        panel.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);
        
        //Etiqueta papa la contraseña
        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        //Botón para iniciar sesión
        btnIngresar = new JButton("Ingresar");
        panel.add(new JLabel()); // espacio vacío
        panel.add(btnIngresar);

        add(panel);

        //Evento del botón
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
    }

    //Método que valida las credenciales ingresadas.
    private void autenticar() {

    //Se obtiene el texto ingresado en el campo de usuario.
    String usuario = txtUsuario.getText();
    //Se obtiene la contraseña ingresada.
    String password = new String(txtPassword.getPassword());

    try {
        //Este método puede lanzar una excepción si las credenciales no coinciden.
        Usuario usuarioAutenticado = sistema.autenticarUsuario(usuario, password);
        JOptionPane.showMessageDialog(this, "Bienvenido " + usuario);

        //Se abre la ventana del menú principal.
        new VentanaMenu(sistema, usuarioAutenticado).setVisible(true);
        
        //Se cierra la ventana actual (Login) para evitar múltiples ventanas abiertas.
        dispose();

    } catch (UsuarioInvalidoException ex) {
        
        //Si ocurre una excepción, se muestra un mensaje de error al usuario.
        JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}