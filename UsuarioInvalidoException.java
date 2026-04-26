package modelo;

/*
 * UsuarioInvalidoException
 *
 * Permite separar claramente errores de negocio
 * (usuario inválido) de errores técnicos
 * como fallos de base de datos.
 */
public class UsuarioInvalidoException extends Exception {

    /*
     * Constructor que recibe un mensaje descriptivo
     * del motivo por el cual la autenticación falló.
     */
    public UsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}