package org.mkaa.view;

import java.util.Scanner;
import org.mkaa.controller.AutoresController;

public class MenuPrincipal {

    Scanner leer = new Scanner(System.in);

    public void iniciar() {
        int opcion = 0;

        do {
            System.out.println("\nBienvenido, seleccione una opcion!");
            System.out.println("1. Modulo Cliente");
            System.out.println("2. Modulo Autores");
            System.out.println("3. Modulo Categorias");
            System.out.println("4. Modulo Editoriales");
            System.out.println("5. Salir");
            
            try {
                opcion = Integer.parseInt(leer.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue; 
            }

            switch (opcion) {
                case 1:
                    System.out.println("Cliente (En construcción...)");
                    break;
                case 2:
                    AutoresController autoresCtrl = new AutoresController();
                    autoresCtrl.iniciar();
                    break;
                case 3:
                    System.out.println("Categoria (En construcción...)");
                    break;
                case 4:
                    System.out.println("Editoriales (En construcción...)");
                    break;
                case 5:
                    System.out.println("Adiós Vaquero!");
                    break;
                default:
                    System.out.println("NO existe esta opción");
            }
        } while (opcion != 5);
    }
}