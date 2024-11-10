package dominio;

import java.util.ArrayList;
import java.io.Serializable;


public class Libreta implements Serializable {
    private ArrayList<Contacto> contactos;

    public Libreta() {
        this.contactos = new ArrayList<>();
    }

    public Libreta annadir(Contacto c) {
        this.contactos.add(c);
        return this;
    }

    public boolean borrar(Contacto contacto) {
        if (!contactos.contains(contacto)) {
            System.out.println("El contacto que desea borrar no existe");
            return false;
        } else {
            contactos.remove(contacto);
            System.out.println("El contacto se ha borrado correctomente");
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Contacto c : contactos) {
            sb.append(c.toString()).append("\n\n");
        }

        return sb.toString();
    }
}
