package interfaz;

import dominio.Contacto;
import dominio.Libreta;

import java.util.Scanner;

import java.io.*;


public class Interfaz {
    private Libreta libreta;
    private Scanner sc = new Scanner(System.in);
    private static String Fichero = "contactos.dat";
    private static String FicheroCSV = "contactos.csv";

    public Interfaz() {
        this.libreta = new Libreta();
        this.sc = new Scanner(System.in);
        cargarContactos();
    }


    public void leerEntrada(String entrada) {
        String[] input = entrada.split(" ");

        if (input[0].equalsIgnoreCase("ayuda") && input.length == 1)
            printAyuda(false);

        if (input[0].equalsIgnoreCase("add") && input.length == 3) {
            String nombre = input[1];
            String telefono = input[2];
            Contacto contacto = new Contacto(nombre, telefono);
            libreta.annadir(contacto);
            System.out.println("Contacto añadido: " + nombre + " - " + telefono);
        } else if (input[0].equalsIgnoreCase("borrar") && input.length == 2) {
            String nombre = input[1];
            libreta.borrar(nombre);
        }  else if (input[0].equalsIgnoreCase("modificar") && input.length == 4) {
            String nombre = input[1];
            String nuevoNombre = input[2];
            String nuevoTelefono = input[3];
            libreta.modificar(nombre, nuevoNombre, nuevoTelefono);
        } else if (input[0].equalsIgnoreCase("list") && input.length == 1) {
            System.out.println("Lista de contactos:\n");
            System.out.println(libreta.toString());
        } else if (input[0].equalsIgnoreCase("exportar") && input.length == 1) {
            libreta.exportar(FicheroCSV);
        } else {
            System.out.println("Comando no reconocido. Use 'add nombre teléfono','borrar nombre teléfono', 'modificar nombre nuevoNombre nuevoTelefono', 'list' o 'ayuda'.");
        }
    }

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

    public static void printAyuda(boolean start) {
        if (start) {
            System.out.println("Bienvenido a la Libreta de Contactos\n");
        }
        System.out.println("Comandos disponibles:");
        System.out.println("  add nombre teléfono                           : para añadir un contacto");
        System.out.println("  borrar nombre                                 : para borrar un contacto");
        System.out.println("  modificar nombre nuevoNombre nuevoTelefono    : para modificar un contacto");
        System.out.println("  exportar                                      : para exportar los contactos a formato CSV");
        System.out.println("  list                                          : para listar todos los contactos");
        System.out.println("  exit                                          : para salir");
        System.out.print("\n");
    }

    private void guardarContactos() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Fichero))) {
            outputStream.writeObject(libreta);
            System.out.println("Contactos guardados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar contactos: " + e.getMessage());
        }
    }

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
