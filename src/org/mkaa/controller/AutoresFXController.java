package org.mkaa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.mkaa.dao.AutoresDAO;
import org.mkaa.dao.impl.AutoresDAOImpl;
import org.mkaa.model.Autores;
import org.mkaa.system.Main;

public class AutoresFXController implements Initializable {

    @FXML
    private TextField txtIdAutor;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextArea txtBiografia;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Autores> tablaAutores;

    private final AutoresDAO autoresDAO = new AutoresDAOImpl();
    private final ObservableList<Autores> listaAutores = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTabla();
        seleccionarFila();
    }

    private void cargarTabla() {
        listaAutores.setAll(autoresDAO.listarTodos());
        tablaAutores.setItems(listaAutores);
    }

    private void seleccionarFila() {
        tablaAutores.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtIdAutor.setText(String.valueOf(newSelection.getIdAutor()));
                        txtNombre.setText(newSelection.getNombreAutor());
                        txtApellido.setText(newSelection.getApellidoAutor());
                        txtNacionalidad.setText(newSelection.getNacionalidad());
                        txtBiografia.setText(newSelection.getBiografia());
                    }
                });
    }

    @FXML
    private void handleGuardar() {
        try {
            if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
                mostrarError("Nombre y Apellido son obligatorios.");
                return;
            }

            Autores autor = new Autores();
            autor.setNombreAutor(txtNombre.getText().trim());
            autor.setApellidoAutor(txtApellido.getText().trim());
            autor.setNacionalidad(txtNacionalidad.getText().trim());
            autor.setBiografia(txtBiografia.getText().trim());

            if (autoresDAO.insertar(autor)) {
                lblMensaje.setText("Autor registrado exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar el autor.");
            }
        } catch (Exception e) {
            mostrarError("Error al guardar: " + e.getMessage());
        }
    }

    @FXML
    private void handleActualizarRegistro() {
        try {
            if (txtIdAutor.getText().isEmpty()) {
                mostrarError("Seleccione un autor de la tabla para modificar.");
                return;
            }
            if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
                mostrarError("Nombre y Apellido son obligatorios.");
                return;
            }

            Autores autor = new Autores();
            autor.setIdAutor(Integer.parseInt(txtIdAutor.getText().trim()));
            autor.setNombreAutor(txtNombre.getText().trim());
            autor.setApellidoAutor(txtApellido.getText().trim());
            autor.setNacionalidad(txtNacionalidad.getText().trim());
            autor.setBiografia(txtBiografia.getText().trim());

            if (autoresDAO.actualizar(autor)) {
                lblMensaje.setText("Autor actualizado correctamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo actualizar el autor.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID del autor debe ser un número válido.");
        } catch (Exception e) {
            mostrarError("Error al actualizar: " + e.getMessage());
        }
    }

    @FXML
    private void handleEliminar() {
        try {
            if (txtIdAutor.getText().isEmpty()) {
                mostrarError("Seleccione un autor de la tabla para eliminar.");
                return;
            }
            int id = Integer.parseInt(txtIdAutor.getText().trim());
            if (autoresDAO.eliminar(id)) {
                lblMensaje.setText("Autor eliminado correctamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo eliminar el autor.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID del autor debe ser un número válido.");
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
        txtIdAutor.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtNacionalidad.clear();
        txtBiografia.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
