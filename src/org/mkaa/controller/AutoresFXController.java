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
import org.mkaa.dao.AutoresDAO;
import org.mkaa.dao.impl.AutoresDAOImpl;
import org.mkaa.model.Autores;
import org.mkaa.system.Main;
public class AutoresFXController implements Initializable {
    
    @FXML
    private TextField txtIdAutor;
    @FXML
    private TextField txtNombreAutor;
    @FXML
    private TextField txtApellidoAutor;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextField txtBiografia;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Autores> tablaAutores;
    @FXML
    private TableColumn<Autores, String> colId;
    @FXML
    private TableColumn<Autores, String> colNombre;
    @FXML
    private TableColumn<Autores, String> colApellido;
    @FXML
    private TableColumn<Autores, String> colNacionalidad;
    @FXML
    private TableColumn<Autores, String> colBiografia;
 
    private final AutoresDAO autoresDAO = new AutoresDAOImpl();
    private final ObservableList<Autores> listaAutores = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        cargarTabla();
        seleccionarFila();
        
      
        txtIdAutor.setEditable(false);
    }
    
    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idAutor"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreAutor"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoAutor"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colBiografia.setCellValueFactory(new PropertyValueFactory<>("biografia"));
        
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
                        txtNombreAutor.setText(newSelection.getNombreAutor());
                        txtApellidoAutor.setText(newSelection.getApellidoAutor());
                        txtNacionalidad.setText(newSelection.getNacionalidad());
                        txtBiografia.setText(newSelection.getBiografia());
                    }
                    
                });
        
    }
    
    
    @FXML
    private void handleGuardar() {
  
        if (txtNombreAutor.getText().isEmpty()
                || txtApellidoAutor.getText().isEmpty()
                || txtNacionalidad.getText().isEmpty()
                || txtBiografia.getText().isEmpty()) {
            mostrarError("Todos los campos son obligatorios.");
            return;
        }
        try {
            Autores autor = new Autores();
     
            autor.setNombreAutor(txtNombreAutor.getText().trim());
            autor.setApellidoAutor(txtApellidoAutor.getText().trim());
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
            Main.cambiarVista("/org/mkaa/view/MenuPrincipalDashboard.fxml");
        } catch (Exception e) {
            mostrarError("Error al volver al menú: " + e.getMessage());
        }
    }
    
    private void limpiarFormulario() {
        txtIdAutor.clear();
        txtNombreAutor.clear();
        txtApellidoAutor.clear();
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