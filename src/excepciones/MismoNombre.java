package excepciones;

import dominio.Contacto;

public class MismoNombre extends Exception {

    public MismoNombre(Contacto contactoMismoNombre) {
        super("Un contacto con el mismo nombre ha sido encontrado: "
            + contactoMismoNombre.getNombre()
            + " (" + contactoMismoNombre.getTelefono() + ")"
        );
    }
}
