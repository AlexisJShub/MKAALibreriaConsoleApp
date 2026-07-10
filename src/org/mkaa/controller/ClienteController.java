package org.mkaa.controller;
 
import org.mkaa.dao.ClienteDAO;
import org.mkaa.dao.impl.ClienteDAOImpl;
import org.mkaa.view.ClienteConsoleView;
 
public class ClienteController {

    private final ClienteDAO dao; 
    private final ClienteConsoleView vista; 

    public ClienteController(ClienteConsoleView vista) { 
        this.dao = new ClienteDAOImpl(); 
        this.vista = vista; 
    }

    public void iniciar() {
        int opcion; 
        do {
             opcion = vista.mostrarMenu();
                if (opcion == 2) {
                 listar(); 
             }
         }while (opcion != 4); 
    }

    private void listar() {
        vista.mostrarListaClientes(dao.listarTodos()); 
    }
}
