package modelo;

//Excepción personalizada que se lanza cuando las credenciales del usuario son inválidas.
public class UsuarioInvalidoException extends Exception {

    public UsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}