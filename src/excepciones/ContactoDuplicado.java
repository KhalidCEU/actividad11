package excepciones;

import dominio.Contacto;

/**
 * Excepción que se lanza cuando se intenta añadir un contacto duplicado a la libreta.
 *
 * Esta excepción, a diferencia de {@link MismoNombre#MismoNombre(Contacto)}, se lanza
 * cuando el nombre del contacto y el número que se quiere añadir son los mismo que el de un contacto
 * existente.
 */
public class ContactoDuplicado extends Exception {
    /**
     * Construye una nueva excepcion ContactoDuplicado con un mensaje.
     *
     * Esta excepcion es lanzada cuando se intenta añadir un contacto con un nombre y número
     * que ya existe en la libreta de contactos.
     *
     * @param contactoDuplicado El objeto Contacto que causa la excepción, que tiene el mismo nombre
     *                          y mismo número.
     */
    public ContactoDuplicado(Contacto contactoDuplicado) {
        super("Contacto duplicado encontrado: " + contactoDuplicado.getNombre()
            + " (" + contactoDuplicado.getTelefono() + ")");
    }
}