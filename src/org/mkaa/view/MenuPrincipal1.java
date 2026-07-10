package org.mkaa.view;

import java.util.Scanner;

public class MenuPrincipal1 {
    
    public void iniciar() {
        Scanner leer = new Scanner(System.in);
        int opcion = 0; 
        
        do {
            System.out.println("Bienvenido, selecciones una opcion!");
            System.out.println("1. Modulo Cliente");
            System.out.println("2. Modulo Autores");
            System.out.println("3. Modulo Categorias");
            System.out.println("4. Modulo Editoriales");
            System.out.println("5. Salir");
            
            opcion = Integer.parseInt(leer.nextLine());
            
            switch (opcion) {
                case 1:
                    System.out.println("Cliente");
                    break;
                case 2:
                    System.out.println("Autor");
                    break;
                case 3:
                    System.out.println("Categoria");
                    break;
                case 4:
                         System.out.println("NO existe esta opción");
                    break;   
                case 5:
                    System.out.println("Adiós Vaquero!");
                    break;
                default:
                  
                  
            }
        } while (opcion != 4);
        
        leer.close();
    }
}