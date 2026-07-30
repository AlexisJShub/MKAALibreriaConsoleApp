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
import javafx.scene.control.TextField;
import org.mkaa.dao.AutoresDAO;
import org.mkaa.dao.impl.AutoresDAOImpl;
import org.mkaa.model.Autores;
import org.mkaa.system.Main;

public class AutoresFXController implements Initializable {

    @FXML
    private TextField txtCui;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtCorreo;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Autoresr> tablaClientes;//Tabla de entidad: cliente

    private final AutoresDAO clienteDAO = new AutoresDAOImpl();
    private final ObservableList<Autores> listaClientes = FXCollections.observableArrayList();//Entidad:Cliente

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTabla();
        seleccionarFila();
    }

    private void cargarTabla() {
        listaClientes.setAll(clienteDAO.listarTodos());
        tablaClientes.setItems(listaClientes);
    }

    private void seleccionarFila() {
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtCui.setText(String.valueOf(newSelection.getCui()));
                        txtNombre.setText(newSelection.getNombre());
                        txtApellido.setText(newSelection.getApellido());
                        txtCorreo.setText(newSelection.getCorreoElectronico());
                    }
                });
    }

    @FXML
    private void handleGuardar() {
        try {
            if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty()
                    || txtApellido.getText().isEmpty() || txtNacionalidad.getText().isEmpty())
                        || txtBiografia.getText().isEmpty()) {
                mostrarError("Todos los campos son obligatorios.");
                return;
            }

            Autores autores = new Autores();
            autores.id_autor(Long.parseLong(txtId.getText().trim()));
            autores.nombre_autor(txtNombre.getText().trim());
            autores.apellido_autor(txtApellido.getText().trim());
            autores.nacionalidad(txtNacionalidad.getText().trim());
            autores.biografia(txtBiografia.getText().trim());
            
            if (autoresDAO.crear(autores)) {
                lblMensaje.setText("Autor registrado exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar el autor.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El Id debe ser un número válido.");
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
            mostrarError("Error al volver al menú: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtNacionalidad.clear();
        txtBiografa.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
