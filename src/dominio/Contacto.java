package dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa un contacto en la libreta de contactos.
 * Esta clase almacena la información básica de un contacto, como su nombre y numero de teléfono.
 *
 * Implementa Serializable para permitir la persistencia de los objetos Contacto.
 */
public class Contacto implements Serializable {

    /**
     * El nombre del contacto.
     */
    private String nombre;

    /**
     * El numero de teléfono del contacto.
     */
    private String telefono;

    /**
     * Constructor por defecto para la clase Contacto.
     */
    Contacto() {}

    /**
     * Construye un nuevo objeto Contacto con el nombre y numero de telefono
     * especificados
     *
     * @param nombre el nombre del contacto
     * @param telefono el numero de telefono del contacto
     */
    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    /**
     * Getter del atributo nombre
     *
     * @return el nombre del contacto
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Getter del atributo telefono
     *
     * @return el numero de telefono del contacto
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * Setter del atributo nombre
     *
     * @param nombre el nombre del contacto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Setter del atributo telefono
     *
     * @param telefono el numero de telefono del contacto
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Contacto contacto = (Contacto) obj;

        return Objects.equals(nombre, contacto.nombre) &&
               Objects.equals(telefono, contacto.telefono);
    }

    /**
     * Devuelve una representacion en String de este Contacto.
     *
     * @return un String (cadena) que contiene el nombre y numero
     *  de teléfono del contacto.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nombre: " ).append(getNombre())
        .append("\n")
        .append("Numero de telefono: ")
        .append(getTelefono());

        return sb.toString();
    }

}
