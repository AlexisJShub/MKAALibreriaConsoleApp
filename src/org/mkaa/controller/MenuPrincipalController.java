package org.mkaa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.application.Platform;
import org.mkaa.system.Main;

public class MenuPrincipalController {

    // Método para abrir la vista de Autores
    @FXML
    private void handleAutores(ActionEvent event) {
        try {
            Main.cambiarVista("/org/mkaa/view/AutoresView.fxml");
        } catch (Exception e) {
            mostrarError("Error al cargar la vista de Autores: " + e.getMessage());
        }
    }

    // Método para abrir la vista de Categorías
    @FXML
    private void handleCategorias(ActionEvent event) {
        try {
            Main.cambiarVista("/org/mkaa/view/CategoriasView.fxml");
        } catch (Exception e) {
            mostrarError("Error al cargar la vista de Categorías: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleEditoriales(ActionEvent event) {
        try {
            Main.cambiarVista("/org/mkaa/view/EditorialView.fxml");
        } catch (Exception e) {
            mostrarError("Error al cargar la vista de Categorías: " + e.getMessage());
        }
    }
    @FXML
    private void handleClientes(ActionEvent event) {
        try {
            Main.cambiarVista("/org/mkaa/view/ClienteView.fxml");
        } catch (Exception e) {
            mostrarError("Error al cargar la vista de Categorías: " + e.getMessage());
        }
    }

    // Método temporal para los botones de Clientes y Editoriales
   

    // Método para cerrar la aplicación
    @FXML
    private void handleSalir(ActionEvent event) {
        Platform.exit(); // Cierra los hilos de JavaFX
        System.exit(0);  // Finaliza la ejecución del programa
    }

    // Método auxiliar para mostrar errores de navegación
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Navegación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}