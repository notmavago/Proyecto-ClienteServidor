package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Representa un usuario del sistema.
public class Usuario implements Serializable {

    private String username;
    private String password;
    private List<Rutina> listaRutinas;

    //Constructor del usuario.
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        this.listaRutinas = new ArrayList<>();
    }

    //Valida si las credenciales coinciden.
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    //Crea una nueva rutina y la agrega al usuario.
    public void crearRutina(Rutina rutina) {
        listaRutinas.add(rutina);
    }

    public List<Rutina> getListaRutinas() {
        return listaRutinas;
    }

    public String getUsername() {
        return username;
    }
}