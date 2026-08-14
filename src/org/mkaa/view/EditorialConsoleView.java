package org.mkaa.view;

import java.util.Scanner;
import java.util.List;
import org.mkaa.model.Editorial;

public class EditorialConsoleView {

    private final Scanner leer = new Scanner(System.in);

    public int mostrarMenu() {
        int opcion;

        System.out.println("-------Gestion de Editoriales---------");
        System.out.println("-1 CREAR NUEVA EDITORIAL-");
        System.out.println("-2 LISTAR EDITORIALES-");
        System.out.println("-3 BUSCAR EDITORIAL POR NIT-");
        System.out.println("-4 MODIFICAR EDITORIAL-");
        System.out.println("-5 ELIMINAR EDITORIAL-");
        System.out.println("-6 REGRESAR MENU PRINCIPAL-");
        System.out.println("-Seleccione una opción-");
        
        try {
            opcion = Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            opcion = 0;
        }
        return opcion;
    }

    public String solicitarNitEditorial() {
        System.out.println("ingrese el NIT de la editorial");
        return leer.nextLine();
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

    public void mostrarEditorial(Editorial editorial) {
        System.out.println("--- DATOS DE LA EDITORIAL ---");
        System.out.println("NIT: " + editorial.getNit()); 
        System.out.println("NOMBRE: " + editorial.getNombreEditorial());
        System.out.println("DIRECCIÓN: " + editorial.getDireccion());
        System.out.println("TELÉFONO: " + editorial.getTelefono());
    }

    public void mostrarListaEditoriales(List<Editorial> editoriales) {
        System.out.println("\n-- LISTA DE EDITORIALES ---");
        
        // Cabecera de la tabla
        System.out.printf("%-10s %-25s %-30s %-15s\n", "NIT", "NOMBRE", "DIRECCIÓN", "TELÉFONO");
        System.out.println("--------------------------------------------------------------------------------");

        for (Editorial editorial : editoriales) {
            // %-10s ahora acepta el NIT perfectamente sin causar errores
            System.out.printf("%-10s %-25s %-30s %-15s\n", 
                editorial.getNit(), 
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