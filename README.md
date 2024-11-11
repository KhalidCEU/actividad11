## Actividad 9 - Agenda de contactos

### Uso

Primero compile el programa con el comando :

```
make
```

Lance el programa :

```
./a.out
```

El CLI se lanzará y leerá automaticamente los datos.

> Para generar documentacion con **Javadoc**, use el comando ```make javadoc```

### Comandos

  ```add <nombre> <telefono>``` : Para **añadir un contacto**.

  ```borrar <nombre>``` : Para **borrar un contacto** a partir de su nombre

  ```modificar <nombre> <nuevoNombre> <nuevoTelefono>``` : Para **modificar un contacto**.

  ```list```: para **listar todos los contactos**.

  ```exportar```: para **exportar todos los contactos a un fichero CSV**.


  ```exit```: para **salir** del programa.

### Nota

> Este programa no está hecho con el fin de uso en producción. Hay varias cosas mejorables como una gestion de errores mas estricta etc, se hizo simplemente como ejercicio para mostrar el funcionamiento básico de una interfaz de texto con sus comandos principales y el uso de persistencia de datos en Java.