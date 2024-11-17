package excepciones;

import dominio.Contacto;

public class ContactoDuplicado extends Exception {

    public ContactoDuplicado(Contacto contactoDuplicado) {
        super("Contacto duplicado encontrado: " + contactoDuplicado.getNombre()
            + " (" + contactoDuplicado.getTelefono() + ")");
    }
}