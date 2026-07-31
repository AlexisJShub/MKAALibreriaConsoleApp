package org.mkaa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.mkaa.dao.CategoriaDAO;
import org.mkaa.dao.impl.CategoriaDAOImpl;
import org.mkaa.model.Categoria;
import org.mkaa.system.Main;

public class CategoriaFXController implements Initializable {
    
     @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    
    private TableView<Categoria> tablaCategoria;//Tabla de entidad: categoria

    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
    private final ObservableList<Categoria> listaCategoria = FXCollections.observableArrayList();//Entidad:Categoria

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTabla();
        seleccionarFila();
    }

    private void cargarTabla() {
        listaCategoria.setAll(categoriaDAO.listarTodos());
        tablaCategoria.setItems(listaCategoria);
    }

    private void seleccionarFila() {
        tablaCategoria.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtId.setText(String.valueOf(newSelection.getId()));
                        txtNombre.setText(newSelection.getNombre());                 
                    }
                });
    }

    @FXML
    private void handleGuardar() {
        try {
            if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty()) {
                   
                mostrarError("Todos los campos son obligatorios.");
                return;
            }

            Categoria categoria = new Categoria();
            categoria.setId(Long.parseLong(txtId.getText().trim()));
            categoria.setNombre(txtNombre.getText().trim());
   
            if (categoriaDAO.crear(categoria)) {
                lblMensaje.setText("Categoria registrado exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar la categoria.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser un número válido.");
        } catch (Exception e) {
            mostrarError("Error al guardar: " + e.getMessage());
        }
    }

    @FXML
    private void handleLimpiar() {
        limpiarFormulario();
        lblMensaje.setText("Tabla limpia");
    }

    @FXML
    private void handleActualizar() {
        cargarTabla();
        lblMensaje.setText("Tabla actualizada.");
    }

    @FXML
    private void handleVolver() {
        try {
            Main.cambiarVista("/org/mkaa/view/MenuPrincipal.fxml");
        } catch (Exception e) {
            mostrarError("Error al volver al menú: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
