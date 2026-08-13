package org.mkaa.controller;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;

import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import org.mkaa.dao.CategoriasDAO;

import org.mkaa.dao.impl.CategoriasDAOImpl;

import org.mkaa.model.Categorias;

import org.mkaa.system.Main;
 
public class CategoriasFXController implements Initializable {
 
    @FXML

    private TextField txtNombreCategoria;

    @FXML

    private Label lblMensaje;

    @FXML

    private TableView<Categorias> tablaCategorias;

    @FXML

    private TableColumn<Categorias, String> colId;

    @FXML

    private TableColumn<Categorias, String> colNombre;
 
    private final CategoriasDAO categoriasDAO = new CategoriasDAOImpl();

    private final ObservableList<Categorias> listaCategorias = FXCollections.observableArrayList();
 
    @Override

    public void initialize(URL location, ResourceBundle resources) {

        configurarTabla();

        cargarTabla();

        seleccionarFila();

    }
 
    private void configurarTabla() {

        colId.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));

    }
 
    private void cargarTabla() {

        listaCategorias.setAll(categoriasDAO.listarTodos());

        tablaCategorias.setItems(listaCategorias);

    }
 
    private void seleccionarFila() {

        tablaCategorias.getSelectionModel().selectedItemProperty().addListener(

                (obs, oldSelection, newSelection) -> {

                    if (newSelection != null) {

                        txtNombreCategoria.setText(newSelection.getNombreCategoria());

                    }

                });

    }
 
    @FXML

    private void handleGuardar() {

        if (txtNombreCategoria.getText().isEmpty()) {

            mostrarError("El nombre de la categoria es obligatorio.");

            return;

        }
 
        try {

            Categorias categoria = new Categorias();

            categoria.setNombreCategoria(txtNombreCategoria.getText().trim());
 
            if (categoriasDAO.insertar(categoria)) {

                lblMensaje.setText("Categoria registrada exitosamente.");

                cargarTabla();

                limpiarFormulario();

            } else {

                mostrarError("No se pudo registrar la categoria.");

            }

        } catch (Exception e) {

            mostrarError("Error al guardar: " + e.getMessage());

        }

    }
 
    @FXML

    private void handleLimpiar() {

        limpiarFormulario();

        lblMensaje.setText("");

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

        mostrarError("Error al volver al menu: " + e.getMessage());

    }

}
 
    private void limpiarFormulario() {

        txtNombreCategoria.clear();

    }
 
    private void mostrarError(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();

    }

}
 