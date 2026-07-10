package org.mkaa.system;

import org.mkaa.view.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        // Creamos la instancia del menú global
        MenuPrincipal menu = new MenuPrincipal();
        
        // Arrancamos la aplicación desde el inicio
        menu.iniciar();
    }
}