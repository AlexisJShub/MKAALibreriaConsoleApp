package org.mkaa.view; // Asegúrate de que este paquete coincida con la carpeta de tus vistas

import java.util.Scanner;
import java.util.List;
import org.mkaa.model.Editorial;

public class EditorialConsoleView {

    private final Scanner leer = new Scanner(System.in);

    // Método para mostrar las opciones de este menú
    public int mostrarMenu() {
        int opcion;

        System.out.println("-------Gestion de Editoriales---------");
        System.out.println("-1 CREAR NUEVA EDITORIAL-");
        System.out.println("-2 LISTAR EDITORIALES-");
        System.out.println("-3 BUSCAR EDITORIAL POR ID-");
        System.out.println("-4 MODIFICAR EDITORIAL-");
        System.out.println("-5 ELIMINAR EDITORIAL-");
        System.out.println("-6 REGRESAR MENU PRINCIPAL-");
        System.out.println("-Seleccione una opción-");
        
        try {
            opcion = Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            opcion = 0; // Por si ingresan una letra por error
        }
        return opcion;
    }

    public int solicitarIdEditorial() {
        System.out.println("ingrese el ID de la editorial");
        try {
            return Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, se enviará 0");
            return 0;
        }
    }

    public String solicitarNombreEditorial() {
        System.out.println("ingrese el Nombre de la editorial");
        return leer.nextLine();
    }

    public String solicitarDireccion() {
        System.out.println("ingrese la Dirección de la editorial");
        return leer.nextLine();
    }

    public String solicitarTelefono() {
        System.out.println("ingrese el Teléfono de la editorial");
        return leer.nextLine();
    }

    // Mostrar el detalle de una EDITORIAL
    public void mostrarEditorial(Editorial editorial) {
        System.out.println("--- DATOS DE LA EDITORIAL ---");
        System.out.println("ID: " + editorial.getIdEditorial()); // Ajusta si el getter se llama diferente
        System.out.println("NOMBRE: " + editorial.getNombreEditorial());
        System.out.println("DIRECCIÓN: " + editorial.getDireccion());
        System.out.println("TELÉFONO: " + editorial.getTelefono());
    }

    // Mostrar la lista de EDITORIALES
    public void mostrarListaEditoriales(List<Editorial> editoriales) {
        System.out.println("\n-- LISTA DE EDITORIALES ---");
        
        // Encabezado formateado con espaciados fijos
        System.out.printf("%-10s %-25s %-30s %-15s\n", "ID", "NOMBRE", "DIRECCIÓN", "TELÉFONO");
        System.out.println("--------------------------------------------------------------------------------");

        for (Editorial editorial : editoriales) {
            System.out.printf("%-10d %-25s %-30s %-15s\n", 
                editorial.getIdEditorial(), 
                editorial.getNombreEditorial(), 
                editorial.getDireccion(), 
                editorial.getTelefono()
            );
        }
        System.out.println();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}