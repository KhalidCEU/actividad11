package dominio;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

import java.io.FileWriter;

import excepciones.*;

/**
 * Representa una libreta de contactos.
 * Esta clase gestiona una colección de objetos Contacto, permitiendo
 * añadir, borrar, modificar y listar contactos.
 *
 * Implementa Serializable para permitir la persistencia de la libreta completa.
 */
public class Libreta implements Serializable {
    /**
     * Lista de contactos almacenados en la libreta.
     * Se usa un ArrayList para permitir un numero variable de contactos
     * y facilitar la adición, eliminación y modificación de los mismos.
     */
    private ArrayList<Contacto> contactos;

    /**
     * Construye un nuevo objeto Libreta con una
     * lista vacia de contactos.
     */
    public Libreta() {
        this.contactos = new ArrayList<>();
    }

    /**
     * Añade un nuevo contacto a la libreta.
     *
     * @param c el objeto Contacto a añadir a la libreta
     * @return esta Libreta
     * @throws ContactoDuplicado si ya existe un contacto con el mismo nombre y número
     * @throws MismoNombre si ya existe un contacto con el mismo nombre pero diferente número
     */
    public Libreta annadir(Contacto c) throws ContactoDuplicado, MismoNombre  {
        for (Contacto contactoExistente : this.contactos) {
            if (c.getNombre().equals(contactoExistente.getNombre())) {
                if (c.getTelefono().equals(contactoExistente.getTelefono())) {
                    throw new ContactoDuplicado(c);
                } else {
                    throw new MismoNombre(c);
                }
            }
        }

        this.contactos.add(c);
        System.out.println("Contacto añadido: " + c.getNombre() + " - " + c.getTelefono());
        return this;
    }

    /**
     * Borra un contacto existente de la libreta a partir de su nombre.
     *
     * @param nombreContacto el nombre del contacto que queremos borrar
     */
    public void borrar(String nombreContacto) {
        Contacto contacto = buscarPorNombre(nombreContacto);

        if (!contactos.contains(contacto)) {
            System.out.println("El contacto que desea borrar no existe");
        } else {
            contactos.remove(contacto);
            System.out.println("El contacto " + contacto.getNombre() + " se ha borrado correctomente");
        }
    }

    /**
     * Modifica un contacto de la libreta.
     *
     * @param nombreContacto el nombre del contacto que se desea modificar
     * @param nuevoNombre el nuevo nombre que se asignará al contacto
     * @param nuevoTelefono el nuevo numero de telefono que se asignará al contacto
     */
    public void modificar(String nombreContacto, String nuevoNombre, String nuevoTelefono) {
        Contacto contacto = buscarPorNombre(nombreContacto);

        if (!contactos.contains(contacto)) {
            System.out.println("El contacto que desea borrar no existe");
        } else {
            contacto.setNombre(nuevoNombre);
            contacto.setTelefono(nuevoTelefono);
            System.out.println("El contacto " + nombreContacto + " se ha modificado correctomente");
        }
    }

    /**
     * Busca un contacto en la libreta por su nombre.
     *
     * @param nombre el nombre del contacto a buscar
     * @return el objeto Contacto si se encuentra, o null si no existe ningun contacto con ese nombre
     */
    private Contacto buscarPorNombre(String nombre) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Exporta todos los contactos de la libreta a un archivo CSV.
     *
     * @param nombreFichero el nombre del archivo CSV donde se exportarán los contactos
     */
    public void exportar(String nombreFichero) {
        try (FileWriter writer = new FileWriter(nombreFichero)) {
            writer.append("Nombre,Teléfono\n");

            for (Contacto contacto : contactos) {
                writer.append(contacto.getNombre())
                      .append(",")
                      .append(contacto.getTelefono())
                      .append("\n");
            }

            System.out.println("Exportación a CSV completada: " + nombreFichero);
        } catch (Exception e) {
            System.out.println("Error al exportar a CSV: " + e.getMessage());
        }
    }

    /**
     * Devuelve una representación en String de todos los contactos en la libreta.
     *
     * @return un String que contiene la información de todos los contactos,
     *         cada uno en su propia línea, con una línea en blanco entre
     *         contactos.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Contacto c : contactos) {
            sb.append(c.toString()).append("\n\n");
        }

        return sb.toString();
    }
}
