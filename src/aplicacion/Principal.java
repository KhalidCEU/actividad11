
package aplicacion;
import interfaz.Interfaz;

/**
 * Clase Principal que contiene el entrypoint (punto de entrada) del programa.
 * Esta clase se usa para iniciar la aplicación.
 */
public class Principal {

    /**
     * Entrypoint del programa.
     * Inicializa el objeto Interfaz y llama su metodo {@link Interfaz#iniciar()}
     *
     * @param args Argumentos de la linea de comandos (no usados).
     */
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        interfaz.iniciar();
    }

}