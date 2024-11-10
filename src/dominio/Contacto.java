package dominio;

import java.io.Serializable;

public class Contacto implements Serializable {
    private String nombre;
    private String telefono;

    Contacto() {}

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nombre: " ).append(getTelefono())
        .append("\n")
        .append("Numero de telefono: ")
        .append(getNombre());

        return sb.toString();
    }

}
