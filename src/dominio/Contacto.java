package dominio;

import java.io.Serializable;

public class Contacto implements Serializable {
    String nombre;
    String numeroDeTelefono;

    Contacto() {}

    public Contacto(String nombre, String numeroDeTelefono) {
        this.nombre = nombre;
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nombre: " ).append(this.nombre).append("\n")
        .append("Numero de telefono: ")
        .append(this.numeroDeTelefono);

        return sb.toString();
    }

}
