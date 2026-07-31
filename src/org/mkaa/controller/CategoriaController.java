package org.mkaa.controller;

import org.mkaa.dao.CategoriaDAO;
import org.mkaa.dao.impl.CategoriaDAOImpl;
import org.mkaa.model.Categoria;
import org.mkaa.view.CategoriaConsoleView;

public class CategoriaController {

    private final CategoriaDAO dao;
    private final CategoriaConsoleView vista;
    private int Id;

    public CategoriaController(CategoriaConsoleView vista) {
        this.dao = new CategoriaDAOImpl();
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
        vista.mostrarListaCategoria(dao.listarTodos());
    }

    private void buscar() {
        long id = vista.solicitarId();
        Categoria categoria = dao.buscarPorId(Id);
        if (categoria != null) {
            vista.mostrarCategoria(categoria);
        } else {
            vista.mostrarMensaje("Categoria no encontrado con el Id: " + id);
        }
    }
}