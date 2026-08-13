package org.mkaa.view;

import java.util.List;
import java.util.Scanner;
import org.mkaa.model.Categorias;

public class CategoriasConsoleView {

    private final Scanner leer = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n");
        System.out.println("___________________________ ");
        System.out.println("_____Gestion Categorias_____");
        System.out.println("1. crear nueva categoria");
        System.out.println("2. listar todas las categorias");
        System.out.println("3. buscar categoria por ID");
        System.out.println("4. modificar categoria");
        System.out.println("5. eliminar categoria");
        System.out.println("6. regresar a menu principal");
        System.out.println("Seleccione una opcion");
        return Integer.parseInt(leer.nextLine());
    }

    public int solicitarID() {
        System.out.println("Ingrese el id de la categoria: ");
        return Integer.parseInt(leer.nextLine());
    }

    public String solicitarNombreCategoria() {
        System.out.println("Ingrese el nombre de la categoria: ");
        return leer.nextLine();
    }

    public void mostrarCategoria(Categorias categoria) {
        System.out.println("_____Datos categoria_____");
        System.out.println("ID: " + categoria.getIdCategoria());
        System.out.println("Nombre: " + categoria.getNombreCategoria());
    }

    public void mostrarListaCategorias(List<Categorias> categorias) {
        System.out.println("_____Lista categorias_____");
        System.out.printf("%-15s %-30s \n", "id", "nombre");
        for (Categorias categoria : categorias) {
            System.out.printf("%-15s %-30s \n", categoria.getIdCategoria(), categoria.getNombreCategoria());
        }
        System.out.println(" --- fin de categorias ---\n");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
