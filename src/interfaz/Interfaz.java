package interfaz;

import dominio.Contacto;
import dominio.Libreta;
import excepciones.*;

import java.util.Scanner;

import java.io.*;

/**
 * Gestiona la interfaz de usuario de la libreta de contactos.
 * Esta clase proporciona metodos para interactuar con el usuario,
 * procesar comandos y realizar operaciones sobre la libreta de contactos.
 */
public class Interfaz {
    private Libreta libreta;
    private Scanner sc = new Scanner(System.in);
    private static String Fichero = "contactos.dat";
    private static String FicheroCSV = "contactos.csv";

    /**
     * Construye una nueva instancia de la Interfaz.
     * Inicializa una nueva Libreta, crea un objeto Scanner para la entrada
     * del usuario, y carga los contactos existentes desde un archivo (si existe).
     */
    public Interfaz() {
        this.libreta = new Libreta();
        this.sc = new Scanner(System.in);
        cargarContactos();
    }


    /**
     * Lee la entrada del usuario y ejecuta el comando correspondiente.
     * Si no existe el comando, se muestra un mensaje indicandolo.
     *
     * @param entrada la línea de comando introducida por el usuario
     */
    public void leerEntrada(String entrada) {
        String[] input = entrada.split(" ");
        String firstArg = input[0].toLowerCase();

        if (firstArg.equals("ayuda") && input.length == 1) {
            printAyuda(false);
            return;
        }

        if (firstArg.equals("exportar") && input.length == 1) {
            libreta.exportar(FicheroCSV);
            return;
        }

        if (firstArg.equals("add") && input.length == 3) {
            String nombre = input[1];
            String telefono = input[2];
            Contacto contacto = new Contacto(nombre, telefono);
            try {
                libreta.annadir(contacto);
            } catch (ContactoDuplicado e) {
                System.out.println("Error al añadir un contacto: " + e.getMessage());
                manejarActualizado(contacto);
            } catch (MismoNombre e) {
                System.out.println("Error al añadir un contacto: " + e.getMessage());
                manejarActualizado(contacto);
            }
        } else if (firstArg.equals("borrar") && input.length == 2) {
            String nombre = input[1];
            libreta.borrar(nombre);
        }  else if (firstArg.equals("modificar") && input.length == 4) {
            String nombre = input[1];
            String nuevoNombre = input[2];
            String nuevoTelefono = input[3];
            libreta.modificar(nombre, nuevoNombre, nuevoTelefono);
        } else if (firstArg.equals("list") && input.length == 1) {
            System.out.println("Lista de contactos:\n");
            System.out.println(libreta.toString());
        } else {
            System.out.println("Comando no reconocido.");
            System.out.println("Use 'add nombre teléfono', 'borrar nombre', 'modificar nombre nuevoNombre nuevoTelefono', 'list' o 'ayuda'.");
        }
    }

    public void manejarActualizado(Contacto c) {
        System.out.println("¿Desea actualizar el contacto existente? [S/N]");

        if (sc.nextLine().equalsIgnoreCase("s")) {
            String nuevoNombre = c.getNombre();
            String nuevoNumero = c.getTelefono();
            boolean cambios = false;

            System.out.println("¿Desea actualizar el nombre del contacto? [S/N]");

            if (sc.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Inserte su nuevo nombre: ");
                nuevoNombre = sc.nextLine();
                cambios = true;
            }

            System.out.println("¿Desea actualizar su número? [S/N]");

            if (sc.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Inserte su nuevo número: ");
                nuevoNumero = sc.nextLine();
                cambios = true;
            }

            if (cambios) {
                System.out.println("Resumen de cambios:");
                System.out.println("Nombre: " + c.getNombre() + " -> " + nuevoNombre);
                System.out.println("Número: " + c.getTelefono() + " -> " + nuevoNumero);

                System.out.println("¿Confirma estos cambios? [S/N]");
                if (sc.nextLine().equalsIgnoreCase("s")) {
                    libreta.modificar(c.getNombre(), nuevoNombre, nuevoNumero);
                    System.out.println("Contacto actualizado correctamente");
                } else {
                    System.out.println("Actualizacion cancelada.");
                }
            } else {
                System.out.println("No se realizaron cambios.");
            }
        } else {
            System.out.println("No se realizaron cambios.");
        }
    }

    /**
     * Inicia la interfaz de línea de comandos para la libreta de contactos.
     * Ejecuta un bucle que:
     * 1. Muestra la ayuda inicial.
     * 2. Solicita y procesa comandos del usuario continuamente.
     * 3. Termina cuando el usuario introduce 'exit'.
     * 4. Guarda los contactos antes de finalizar.
     *
     * Los comandos disponibles se procesan en el método {@link #leerEntrada(String)}
     * Al finalizar, se cierra el Scanner y se muestra un mensaje de despedida.
     */
    public void iniciar() {
        printAyuda(true);

        while (true) {
            System.out.print("> ");
            String entrada = sc.nextLine().trim();

            if (entrada.equalsIgnoreCase("exit")) {
                break;
            }
            leerEntrada(entrada);
        }

        guardarContactos();
        System.out.println("Hasta la proxima");
        sc.close();
    }

    /**
     * Muestra un mensaje con todos los comandos dispobibles;
     *
     * @param start indica si se llama al comenzar el programa (true) o no (false).
     *              Si es true, también muestra el mensaje de bienvenida.
     */
    public static void printAyuda(boolean start) {
        if (start) {
            System.out.println("Bienvenido a la Libreta de Contactos\n");
        }
        System.out.println("Comandos disponibles:");
        System.out.println("  add <nombre> <teléfono>                           : para añadir un contacto");
        System.out.println("  borrar <nombre>                                   : para borrar un contacto");
        System.out.println("  modificar <nombre> <nuevoNombre> <nuevoTelefono>  : para modificar un contacto");
        System.out.println("  list                                              : para listar todos los contactos");
        System.out.println("  exportar                                          : para exportar los contactos a formato CSV");
        System.out.println("  exit                                              : para salir");
        System.out.print("\n");
    }

    /**
     * Guarda los contactos de la libreta en un archivo.
     * Utiliza serialización de objetos mediante un ObjectOutputStream para almacenar la libreta completa.
     * Si ocurre algun error durante el proceso de guardado, se muestra un mensaje de error.
     */
    private void guardarContactos() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Fichero))) {
            outputStream.writeObject(libreta);
            System.out.println("Contactos guardados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar contactos: " + e.getMessage());
        }
    }

    /**
     * Carga los contactos desde un archivo a la libreta.
     * Si el archivo existe, intenta deserializar la libreta desde él mediante un ObjectInputStream .
     * Si ocurre algun error durante la carga, se crea una nueva libreta vacia.
     * Si el archivo no existe, no se realiza ninguna acción (la libreta permanece vacia).
     */
    private void cargarContactos() {
        File file = new File(Fichero);

        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Fichero))) {
                libreta = (Libreta) inputStream.readObject();
                System.out.println("Contactos cargados correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cargar contactos: " + e.getMessage());
                libreta = new Libreta();
            }
        }
    }
}
