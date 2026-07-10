package org.mkaa.controller;

import org.mkaa.dao.impl.EditorialDAOImpl;
import org.mkaa.view.EditorialConsoleView;
import org.mkaa.dao.impl.EditorialDAO;

public class EditorialController {

    private final EditorialDAO dao;
    private final EditorialConsoleView vista;

    public EditorialController(EditorialConsoleView vista) {
        this.dao = new EditorialDAOImpl();
        this.vista = vista;
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            if (opcion == 2) {
                listar();
            }
        } while (opcion != 4);
    }

    private void listar() {
        vista.mostrarListaEditoriales(dao.listarTodos());
    }
}