package org.mkaa.view;

import java.util.List;
import java.util.Scanner;
import org.mkaa.model.Categoria;

public class CategoriaConsoleView {
    private final Scanner leer = new Scanner(System.in);
    public int mostrarMenu() {
        int opcion = 0;
        System.out.println("--- GESTION DE CATEGORIAS ---");
        System.out.println("-1 CREAR nueva Categoría ---");
        System.out.println("-2 LISTAR todas las Categorías ---");
        System.out.println("-3 BUSCAR Categoría por ID ---");
        System.out.println("-4 MODIFICAR Categoría ---");
        System.out.println("-5 ELIMINAR Categoría ---");
        System.out.println("-6 REGRESAR a menú PRINCIPAL ---");
        System.out.println("SELECCIONE UNA OPCION --->");

        opcion = Integer.parseInt(leer.nextLine());
        return opcion;

    }

    public int solicitarId() {
        System.out.println("Ingrese el ID de la categoría: ");
        return Integer.parseInt(leer.nextLine());

    }

    public String solicitarNombreCategoria() {
        System.out.println("Ingrese el NOMBRE de la categoría: ");
        return leer.nextLine();

    }

    public void mostrarCategoria(Categoria categoria) {
        System.out.println("--- DATOS DE LA CATEGORIA ---");
        System.out.println("ID: " + categoria.getIdCategoria());
        System.out.println("NOMBRE: " + categoria.getNombreCategoria());
    }

    public void mostrarListaCategorias(List<Categoria> categorias) {
        System.out.println("--- LISTA DE CATEGORIAS ---");
        System.out.printf("%-10s %-20s\n", "ID", "NOMBRE");
        for (Categoria categoria : categorias) {
            System.out.printf("%-10s %-20s%n",
        categoria.getIdCategoria(),
        categoria.getNombreCategoria()
        );

        }

    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);

    }

    public long solicitarIdCategoria() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
 
 
