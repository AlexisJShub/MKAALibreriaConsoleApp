package org.mkaa.controller;
 
import org.mkaa.dao.ClienteDAO;
import org.mkaa.dao.impl.ClienteDAOImpl;
import org.mkaa.model.Clientes;
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
            switch (opcion) {
                case 1:
 
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
 
                    break;
                case 5:
 
                    break;
                case 6:
 
                    break;
                default:
                    throw new AssertionError();
            }
        } while (opcion != 6);
    }
 
    private void listar() {
        vista.mostrarListaClientes(dao.listarTodos());
    }
 
    private void buscar() {
        long cui = vista.solicitarCUI();
        Clientes cliente = dao.buscarPorId(cui);
        if (cliente != null) {
            vista.mostrarCliente(cliente);
        } else {
            vista.mostrarMensaje("Cliente no encontrado con el CUI: " + cui);
        }
    }
}
 