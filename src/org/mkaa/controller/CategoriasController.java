package org.mkaa.controller;

import org.mkaa.dao.CategoriasDAO;
import org.mkaa.dao.impl.CategoriasDAOImpl;
import org.mkaa.model.Categorias;
import org.mkaa.view.CategoriasConsoleView;

public class CategoriasController {

    private final CategoriasDAO dao;
    private final CategoriasConsoleView vista;

    public CategoriasController(CategoriasConsoleView vista) {
        this.dao = new CategoriasDAOImpl();
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
                    vista.mostrarMensaje("Opcion invalida, intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private void listar() {
        vista.mostrarListaCategorias(dao.listarTodos());
    }

    private void buscar() {
        int idCategoria = vista.solicitarID();
        Categorias categoria = dao.buscarPorId(idCategoria);
        if (categoria != null) {
            vista.mostrarCategoria(categoria);
        } else {
            vista.mostrarMensaje("Categoria no encontrada por el id: " + idCategoria);
        }
    }
}