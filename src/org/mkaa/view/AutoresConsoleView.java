package org.mkaa.view;

import java.util.List;
import java.util.Scanner;
import org.mkaa.model.Autores;

public class AutoresConsoleView {

    private final Scanner leer = new Scanner(System.in); 

    // Método para mostrar las opciones de este menú 
    public int mostrarMenu() {
        int opcion = 0; 
        
        System.out.println("_____Gestión Autores_____");
        System.out.println("1. Crear nuevo autor");
        System.out.println("2. Listar todos los autores");
        System.out.println("3. Buscar autor por ID");
        System.out.println("4. Modificar autor");
        System.out.println("5. Eliminar autor");
        System.out.println("6. Regresar al menú principal");
        System.out.println("Seleccione una opción: ");
        
        opcion = Integer.parseInt(leer.nextLine()); 
        return opcion; 
    }

    // Solicitar ID del autor
    public int solicitarIdAutor() {
        System.out.println("Ingrese el ID del autor: ");
        return Integer.parseInt(leer.nextLine()); 
    }

    // Solicitar Nombre del autor
    public String solicitarNombreAutor() {
        System.out.println("Ingrese el nombre del autor: ");
        return leer.nextLine(); 
    }

    // Solicitar Apellido del autor
    public String solicitarApellidoAutor() {
        System.out.println("Ingrese el apellido del autor: ");
        return leer.nextLine(); 
    }

    // Solicitar Nacionalidad
    public String solicitarNacionalidad() {
        System.out.println("Ingrese la nacionalidad del autor: ");
        return leer.nextLine(); 
    }
    
    // Solicitar Biografía
    public String solicitarBiografia() {
        System.out.println("Ingrese una breve biografía del autor: ");
        return leer.nextLine(); 
    }

    // Mostrar el detalle de un autor único
    public void mostrarAutor(Autores autor) {
        System.out.println("_____Datos del Autor_____");
        System.out.println("ID: " + autor.getIdAutor()); 
        System.out.println("Nombre: " + autor.getNombreAutor()); 
        System.out.println("Apellido: " + autor.getApellidoAutor()); 
        System.out.println("Nacionalidad: " + autor.getNacionalidad()); 
        System.out.println("Biografía: " + autor.getBiografia()); 
        System.out.println("---\n");
    }

    // Mostrar lista completa de autores en formato tabla
    public void mostrarListaAutores(List<Autores> autores) {
        System.out.println("_____Lista de Autores_____");
        
        // Tabla usando la propiedad %-[tamaño columna]s
        System.out.printf("%-5s | %-15s | %-15s | %-15s | %-20s\n", 
                          "ID", "Nombre", "Apellido", "Nacionalidad", "Biografía");

        for (Autores autor : autores) {
            System.out.printf("%-5s | %-15s | %-15s | %-15s | %-20s\n",
                              autor.getIdAutor(), 
                              autor.getNombreAutor(), 
                              autor.getApellidoAutor(), 
                              autor.getNacionalidad(),
                              autor.getBiografia()); 
        }
        System.out.println(" --- Fin de autores ---\n");
    }

    // Para mostrar un mensaje personalizado de éxito o error en la consola
    public void mostrarMensaje(String mensaje) { 
        System.out.println(mensaje);
    }
}