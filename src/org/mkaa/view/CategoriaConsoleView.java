package org.mkaa.view;

import java.util.List;
import java.util.Scanner;
import org.mkaa.model.Categoria;

public class CategoriaConsoleView {

    private final Scanner leer = new Scanner(System.in);

    //metodo para mostrar las opcion de este menu
    public int mostrarMenu() {
        int opcion = 0;
        // todo el menu
        System.out.println("--- GESTION DE CATEGORIA ---");
        System.out.println("-1 CREAR nueva categoria ---");
        System.out.println("-2 LISTAR todos las categorias ---");
        System.out.println("-3 BUSCAR categoria por ID ---");
        System.out.println("-4 MODIFICAR categoria ---");
        System.out.println("-5 ELIMINAR nueva categoria ---");
        System.out.println("-6 REGRESAR a menú PRIMCIPAL ---");
        System.out.print("SELECCIONE UNA OPCION -->");
        opcion = Integer.parseInt(leer.nextLine());
        return opcion;
    }

    public long solicitarId() {
        System.out.println("Ingrese el Id de la catgeoria: ");
        return Long.parseLong(leer.nextLine());
    }

    //nombreCliente
    public String solicitarNombreCategoria() {
        String nombre;
        System.out.println("Ingres el NOMBRE de la categoria");
        nombre = leer.nextLine();
        return nombre;
        //return leer.nextLine();
    }

    
    //mostrar el detalle de un CLIENTE
    public void mostrarCategoria(Categoria categoria){
        System.out.println("--- DATOS DEL CATEGORIA ---");
        System.out.println("Id: " + categoria.getId());
        System.out.println("NOMBRE: " + categoria.getNombre());
        System.out.println("---\n");
    }
    
    public void mostrarListaCategoria(List<Categoria> categoria){
    System.out.println("--- LISTA DE CATEGORIA ---");
    System.out.printf("%-15s %-10s %-10s %-10s\n", "ID","NOMBRE");

    for (Categoria cat : categoria) {
        System.out.printf("%-10s %-10s %-10s %-10s\n",
                cat.getId(), cat.getNombre());
    }
    System.out.println(" --- fin de categoria ---\n");
}
    
    //para mostrar mensaje personalizado
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}
