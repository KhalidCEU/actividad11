package dominio;

import java.util.ArrayList;
import java.io.Serializable;

import java.io.FileWriter;

public class Libreta implements Serializable {
    private ArrayList<Contacto> contactos;

    public Libreta() {
        this.contactos = new ArrayList<>();
    }

    public Libreta annadir(Contacto c) {
        this.contactos.add(c);
        return this;
    }

    public void borrar(String nombreContacto) {
        Contacto contacto = buscarPorNombre(nombreContacto);

        if (!contactos.contains(contacto)) {
            System.out.println("El contacto que desea borrar no existe");
        } else {
            contactos.remove(contacto);
            System.out.println("El contacto " + contacto.getNombre() + " se ha borrado correctomente");
        }
    }

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

    private Contacto buscarPorNombre(String nombre) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Contacto c : contactos) {
            sb.append(c.toString()).append("\n\n");
        }

        return sb.toString();
    }
}
