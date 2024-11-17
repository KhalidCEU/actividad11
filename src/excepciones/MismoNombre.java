package excepciones;

import dominio.Contacto;

/**
 * Excepción que se lanza cuando se intenta añadir un contacto con el mismo nombre a la libreta.
 *
 * Esta excepción, a diferencia de {@link ContactoDuplicado#ContactoDuplicado(Contacto)}, solo
 * se lanza cuando únicamente el nombre del contacto que se quiere añadir es el mismo que uno existente.
 */
public class MismoNombre extends Exception {
    /**
     * Construye una nueva excepción Mismonombre con un mensaje.
     *
     * Esta excepción es lanzada cuando se intenta añadir un contacto con un nombre que ya
     * existe en la libreta de contactos, pero con un número diferente.
     *
     * @param contactoMismoNombre El objeto Contacto que causa la excepción, que tiene el mismo nombre.
     */
    public MismoNombre(Contacto contactoMismoNombre) {
        super("Un contacto con el mismo nombre ha sido encontrado: "
            + contactoMismoNombre.getNombre()
            + " (" + contactoMismoNombre.getTelefono() + ")"
        );
    }
}
