## Actividad 11 - Agenda de contactos con manejo de excepciones

Esta actividad es un fork de la [actividad 9](https://github.com/KhalidCEU/actividad9) con los siguientes cambios:
   - **Implementación de excepciones** (customizadas) añadido, para manejar casos especificos como contactos duplicados o nombres repetidos.

  - **Mejora en la interfaz de usuario**: Esta mejora le da la posibilidad al usuario de modificar (o no) el nombre y/o el número de un contacto existente cuando se intenta añadir uno que ya existe.

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