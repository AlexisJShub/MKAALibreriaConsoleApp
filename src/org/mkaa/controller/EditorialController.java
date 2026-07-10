package org.mkaa.controller;

import java.util.List;
import org.mkaa.dao.EditorialDAO;
import org.mkaa.dao.impl.EditorialDAOImpl;
import org.mkaa.model.Editorial;
import org.mkaa.view.EditorialConsoleView;

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
            switch (opcion) {
                case 1 -> crear();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> modificar();
                case 5 -> eliminar();
                case 6 -> vista.mostrarMensaje("Saliendo al Menú Principal...");
                default -> vista.mostrarMensaje("Opción incorrecta. Intenta de nuevo.");
            }
        } while (opcion != 6);
    }

    private void listar() {
        List<Editorial> lista = dao.listarTodos();
        if (lista.isEmpty()) {
            vista.mostrarMensaje("No hay editoriales registradas.");
        } else {
            vista.mostrarListaEditoriales(lista);
        }
    }

    private void crear() {
        vista.mostrarMensaje("\n--- CREAR NUEVA EDITORIAL ---");
        String nit = vista.solicitarNitEditorial();
        String nombre = vista.solicitarNombreEditorial();
        String direccion = vista.solicitarDireccion();
        String telefono = vista.solicitarTelefono();

        Editorial nueva = new Editorial(nit, nombre, direccion, telefono);
        if (dao.insertar(nueva)) {
            vista.mostrarMensaje("¡Editorial creada con éxito!\n");
        } else {
            vista.mostrarMensaje("Error al crear la editorial. Verifique si el NIT ya existe.\n");
        }
    }

    private void buscar() {
        vista.mostrarMensaje("\n--- BUSCAR EDITORIAL ---");
        String nit = vista.solicitarNitEditorial();
        Editorial encontrada = dao.buscar(nit);
        if (encontrada != null) {
            vista.mostrarEditorial(encontrada);
            vista.mostrarMensaje("");
        } else {
            vista.mostrarMensaje("Editorial no encontrada con el NIT proporcionado.\n");
        }
    }

    private void modificar() {
        vista.mostrarMensaje("\n--- MODIFICAR EDITORIAL ---");
        String nit = vista.solicitarNitEditorial();
        Editorial existente = dao.buscar(nit);
        
        if (existente != null) {
            vista.mostrarMensaje("Datos actuales:");
            vista.mostrarEditorial(existente);
            
            vista.mostrarMensaje("\nIngrese los nuevos datos:");
            String nuevoNombre = vista.solicitarNombreEditorial();
            String nuevaDireccion = vista.solicitarDireccion();
            String nuevoTelefono = vista.solicitarTelefono();

            existente.setNombreEditorial(nuevoNombre);
            existente.setDireccion(nuevaDireccion);
            existente.setTelefono(nuevoTelefono);

            if (dao.actualizar(existente)) {
                vista.mostrarMensaje("¡Editorial modificada con éxito!\n");
            } else {
                vista.mostrarMensaje("Error al actualizar la editorial.\n");
            }
        } else {
            vista.mostrarMensaje("No se puede modificar. La editorial no existe.\n");
        }
    }

    private void eliminar() {
        vista.mostrarMensaje("\n--- ELIMINAR EDITORIAL ---");
        String nit = vista.solicitarNitEditorial();
        Editorial existente = dao.buscar(nit);

        if (existente != null) {
            if (dao.eliminar(nit)) {
                vista.mostrarMensaje("¡Editorial eliminada con éxito!\n");
            } else {
                vista.mostrarMensaje("Error al eliminar la editorial.\n");
            }
        } else {
            vista.mostrarMensaje("No se puede eliminar. La editorial no existe.\n");
        }
    }
}