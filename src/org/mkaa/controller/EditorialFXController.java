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
import org.mkaa.dao.EditorialDAO;
import org.mkaa.dao.impl.EditorialDAOImpl;
import org.mkaa.model.Editorial;
import org.mkaa.system.Main;

public class EditorialFXController implements Initializable {

    @FXML
    private TextField txtNit;
    @FXML
    private TextField txtNombreEditorial;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private Label lblMensaje;

    @FXML
    private TableView<Editorial> tablaEditoriales;
    @FXML
    private TableColumn<Editorial, String> colNit;
    @FXML
    private TableColumn<Editorial, String> colNombre;
    @FXML
    private TableColumn<Editorial, String> colDireccion;
    @FXML
    private TableColumn<Editorial, String> colTelefono;

    private final EditorialDAO editorialDAO = new EditorialDAOImpl();
    private final ObservableList<Editorial> listaEditoriales = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Mapeo con los atributos del modelo Editorial
        colNit.setCellValueFactory(new PropertyValueFactory<>("nit"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEditorial"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        cargarTabla();
        seleccionarFila();
    }

    private void cargarTabla() {
        try {
            listaEditoriales.setAll(editorialDAO.listarTodos());
            tablaEditoriales.setItems(listaEditoriales);
        } catch (Exception e) {
            mostrarError("Error al cargar las editoriales: " + e.getMessage());
        }
    }

    private void seleccionarFila() {
        tablaEditoriales.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtNit.setText(newSelection.getNit());
                        txtNombreEditorial.setText(newSelection.getNombreEditorial());
                        txtDireccion.setText(newSelection.getDireccion());
                        txtTelefono.setText(newSelection.getTelefono());
                    }
                });
    }

    @FXML
    private void handleGuardar() {
        try {
            if (txtNit.getText().trim().isEmpty() || txtNombreEditorial.getText().trim().isEmpty()
                    || txtDireccion.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty()) {
                mostrarError("Todos los campos son obligatorios.");
                return;
            }

            Editorial editorial = new Editorial(
                txtNit.getText().trim(),
                txtNombreEditorial.getText().trim(),
                txtDireccion.getText().trim(),
                txtTelefono.getText().trim()
            );

            if (editorialDAO.insertar(editorial)) {
                lblMensaje.setText("Editorial registrada exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar la editorial.");
            }
        } catch (Exception e) {
            mostrarError("Error al guardar: " + e.getMessage());
        }
    }

    @FXML
    private void handleActualizarRegistro() {
        try {
            if (txtNit.getText().trim().isEmpty()) {
                mostrarError("Seleccione una editorial de la tabla para modificar.");
                return;
            }
            if (txtNombreEditorial.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty()) {
                mostrarError("Todos los campos son obligatorios.");
                return;
            }

            Editorial editorial = new Editorial(
                txtNit.getText().trim(),
                txtNombreEditorial.getText().trim(),
                txtDireccion.getText().trim(),
                txtTelefono.getText().trim()
            );

            if (editorialDAO.actualizar(editorial)) {
                lblMensaje.setText("Editorial actualizada correctamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo actualizar la editorial.");
            }
        } catch (Exception e) {
            mostrarError("Error al actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleEliminar() {
        try {
            String nit = txtNit.getText().trim();
            if (nit.isEmpty()) {
                mostrarError("Seleccione una editorial de la tabla para eliminar.");
                return;
            }
            
            if (editorialDAO.eliminar(nit)) {
                lblMensaje.setText("Editorial eliminada correctamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo eliminar la editorial.");
            }
        } catch (Exception e) {
            mostrarError("Error al eliminar: " + e.getMessage());
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
            mostrarError("Error al volver al menú: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtNit.clear();
        txtNombreEditorial.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        tablaEditoriales.getSelectionModel().clearSelection();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}