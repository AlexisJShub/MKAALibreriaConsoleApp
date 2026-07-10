package org.mkaa.controller;

import org.mkaa.model.Autores;
import org.mkaa.view.AutoresConsoleView;
import org.mkaa.dao.AutoresDAO;
import org.mkaa.dao.impl.AutoresDAOImpl;

import java.util.List;

public class AutoresController {

    private final AutoresConsoleView vista;
    private final AutoresDAO dao;
    
    public AutoresController() {
        this.vista = new AutoresConsoleView();
        this.dao = new AutoresDAOImpl();
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    crearAutor();
                    break;
                case 2:
                    listarAutores();
                    break;
                case 3:
                    buscarAutorPorId();
                    break;
                case 4:
                    modificarAutor();
                    break;
                case 5:
                    eliminarAutor();
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


    private void crearAutor() {
        vista.mostrarMensaje("\n--- Registrar Nuevo Autor ---");
        String nombre = vista.solicitarNombreAutor();
        String apellido = vista.solicitarApellidoAutor();
        String nacionalidad = vista.solicitarNacionalidad();
        String biografia = vista.solicitarBiografia();
        
        Autores nuevoAutor = new Autores(0, nombre, apellido, nacionalidad, biografia);

        boolean exito = dao.insertar(nuevoAutor);
        if (exito) {
            vista.mostrarMensaje("¡Autor registrado exitosamente!");
        } else {
            vista.mostrarMensaje("Error: No se pudo registrar el autor.");
        }
    }

    
    private void listarAutores() {
        List<Autores> lista = dao.listarTodos();
        if (lista == null || lista.isEmpty()) {
            vista.mostrarMensaje("No se encontraron autores registrados en la base de datos.");
        } else {
            vista.mostrarListaAutores(lista);
        }
    }

    private void buscarAutorPorId() {
        vista.mostrarMensaje("\n--- Buscar Autor ---");
        int id = vista.solicitarIdAutor();
        Autores autor = dao.buscarPorId(id);

        if (autor != null) {
            vista.mostrarAutor(autor);
        } else {
            vista.mostrarMensaje("Error: No existe ningún autor con el ID " + id);
        }
    }


    private void modificarAutor() {
        vista.mostrarMensaje("\n--- Modificar Autor ---");
        int id = vista.solicitarIdAutor();
        Autores autorExistente = dao.buscarPorId(id);

        if (autorExistente != null) {
            vista.mostrarMensaje("Ingrese los nuevos datos para el autor:");
            String nuevoNombre = vista.solicitarNombreAutor();
            String nuevoApellido = vista.solicitarApellidoAutor();
            String nuevaNacionalidad = vista.solicitarNacionalidad();
            String nuevaBiografia = vista.solicitarBiografia();

            Autores autorActualizado = new Autores(id, nuevoNombre, nuevoApellido, nuevaNacionalidad, nuevaBiografia);
            
            boolean exito = dao.actualizar(autorActualizado);
            if (exito) {
                vista.mostrarMensaje("¡Autor actualizado correctamente!");
            } else {
                vista.mostrarMensaje("Error: No se pudo actualizar el autor.");
            }
        } else {
            vista.mostrarMensaje("Error: El autor con ID " + id + " no existe.");
        }
    }

 
    private void eliminarAutor() {
        vista.mostrarMensaje("\n--- Eliminar Autor ---");
        int id = vista.solicitarIdAutor();
        Autores autorExistente = dao.buscarPorId(id);

        if (autorExistente != null) {
            boolean exito = dao.eliminar(id);
            if (exito) {
                vista.mostrarMensaje("¡Autor eliminado correctamente de la base de datos!");
            } else {
                vista.mostrarMensaje("Error: No se pudo eliminar el autor.");
            }
        } else {
            vista.mostrarMensaje("Error: El autor con ID " + id + " no existe.");
        }
    }
}