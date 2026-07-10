package org.mkaa.controller;

import org.mkaa.dao.CategoriaDAO;
import org.mkaa.dao.impl.CategoriaDAOImpl;
import org.mkaa.model.Categoria;
import org.mkaa.view.CategoriaConsoleView;

public class CategoriaController {

    private final CategoriaDAO dao;
    private final CategoriaConsoleView vista;

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
                    crear();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    modificar();
                    break;
                case 5:
                    eliminar();
                    break;
                case 6:
                    vista.mostrarMensaje("Regresando al menú principal...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 6);
    }

    private void crear() {
        long idCategoria = vista.solicitarIdCategoria();
        String nombreCategoria = vista.solicitarNombreCategoria();

        Categoria nuevaCategoria = new Categoria((int) idCategoria, nombreCategoria);

        boolean creada = dao.crear(nuevaCategoria);
        if (creada) {
            vista.mostrarMensaje("¡Categoría creada exitosamente!");
        } else {
            vista.mostrarMensaje("Error: No se pudo crear la categoría.");
        }
    }

    private void listar() {
        vista.mostrarListaCategorias(dao.listarTodos());
    }

    private void buscar() {
        long idCategoria = vista.solicitarIdCategoria();
        Categoria categoria = dao.buscarPorId(idCategoria);
        if (categoria != null) {
            vista.mostrarCategoria(categoria);
        } else {
            vista.mostrarMensaje("Categoría no encontrada con el ID: " + idCategoria);
        }
    }

    private void modificar() {
        long idCategoria = vista.solicitarIdCategoria();
        Categoria categoria = dao.buscarPorId(idCategoria);

        if (categoria != null) {
            vista.mostrarMensaje("--- Modificando datos de la categoría ---");
            String nuevoNombre = vista.solicitarNombreCategoria();

            categoria.setNombreCategoria(nuevoNombre);

            boolean modificada = dao.modificar(categoria);
            if (modificada) {
                vista.mostrarMensaje("¡Categoría modificada exitosamente!");
            } else {
                vista.mostrarMensaje("Error: No se pudieron guardar los cambios.");
            }
        } else {
            vista.mostrarMensaje("Categoría no encontrada con el ID: " + idCategoria);
        }
    }

    private void eliminar() {
        long idCategoria = vista.solicitarIdCategoria();
        Categoria categoria = dao.buscarPorId(idCategoria);

        if (categoria != null) {
            boolean eliminada = dao.eliminar(idCategoria);
            if (eliminada) {
                vista.mostrarMensaje("¡Categoría eliminada correctamente!");
            } else {
                vista.mostrarMensaje("Error: No se pudo eliminar la categoría.");
            }
        } else {
            vista.mostrarMensaje("Categoría no encontrada con el ID: " + idCategoria);
        }
    }
}
