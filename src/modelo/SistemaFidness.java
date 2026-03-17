package modelo;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

//Clase que administra usuarios y ejercicios del sistema.
public class SistemaFidness implements Serializable {

    private List<Usuario> listaUsuarios;
    private List<Ejercicio> listaEjercicios;

    //Constructor del sistema.
    public SistemaFidness() {
        listaUsuarios = new ArrayList<>();
        listaEjercicios = new ArrayList<>();
    }

    //Registra un nuevo usuario.
    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    //Autentica un usuario en el sistema.
    //@throws UsuarioInvalidoException si las credenciales no coinciden.
    public Usuario autenticarUsuario(String username, String password) 
        throws UsuarioInvalidoException {

    for (Usuario u : listaUsuarios) {
        if (u.login(username, password)) {
            return u;
        }
    }

    throw new UsuarioInvalidoException("Usuario o contraseña incorrectos");
    }

    //Agrega ejercicios disponibles al sistema.
    public void agregarEjercicio(Ejercicio ejercicio) {
        listaEjercicios.add(ejercicio);
    }

    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }
    
    //Guarda el sistema en un archivo.
    public void guardarSistema(String ruta) {

        try (ObjectOutputStream oos = 
             new ObjectOutputStream(new FileOutputStream(ruta))) {

            oos.writeObject(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Carga el sistema desde un archivo.
    public static SistemaFidness cargarSistema(String ruta) {

        try (ObjectInputStream ois = 
             new ObjectInputStream(new FileInputStream(ruta))) {

            return (SistemaFidness) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            return new SistemaFidness();
        }
    }
}