package org.mkaa.view;

import java.util.Scanner;
import org.mkaa.controller.EditorialController; // Conecta con tu controlador de editoriales

public class MenuPrincipal {

    Scanner leer = new Scanner(System.in);

    public void iniciar() {
        int opcion = 0;

        do {
            System.out.println("Bienvenido, seleccione una opcion!");
            System.out.println("1. Modulo Cliente");
            System.out.println("2. Modulo Autores");
            System.out.println("3. Modulo Categorias");
            System.out.println("4. Modulo Editoriales"); // ¡Corregido el paréntesis aquí!
            System.out.println("5. Salir");

            try {
                opcion = Integer.parseInt(leer.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1 -> System.out.println("Modulo Cliente");
                case 2 -> System.out.println("Modulo Autores");
                case 3 -> System.out.println("Modulo Categorias");
                case 4 -> {
                    System.out.println("Abriendo Modulo Editoriales...\n");
                    
                    // Instanciamos tu vista y controlador de editoriales
                    EditorialConsoleView vistaEditorial = new EditorialConsoleView();
                    EditorialController controladorEd = new EditorialController(vistaEditorial);
                    
                    // Le cede el control a tu sub-menú de editoriales
                    controladorEd.iniciar();
                }
                case 5 -> System.out.println("Adiós Vaquero!");
                default -> System.out.println("NO existe esta opción");
            }
        } while (opcion != 5);
    }
}