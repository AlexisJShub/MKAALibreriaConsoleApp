package org.mkaa.controller;
 
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mkaa.dao.UsuarioDao;
import org.mkaa.model.Usuario;
 
public class InicioSesionController implements Initializable {
 
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnIniciarSesion;
    @FXML private Label lblMensaje;
 
    @FXML private TextField txtNuevoUsuario;
    @FXML private PasswordField txtNuevaPassword;
    @FXML private Label lblMensajeRegistro;
 
    private UsuarioDao usuarioDAO;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDAO = new UsuarioDao();
        if (lblMensaje != null) lblMensaje.setText("");
        if (lblMensajeRegistro != null) lblMensajeRegistro.setText("");
    }
 
    @FXML
    public void eventoInicioSesion(ActionEvent evento) {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();
 
        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Por favor, complete todos sus datos.");
            return;
        }
 
      
        String passwordHash = encriptarSHA256(password);
 
      
        Usuario usuarioIniciado = usuarioDAO.iniciarSesion(usuario, passwordHash);
 
        if (usuarioIniciado != null) {
            lblMensaje.setText("Inicio correcto");
            abrirDashBoard(usuarioIniciado);
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos");
        }
    }
 
    @FXML
    public void handleAbrirRegistro() {
        cambiarVista("/org/mkaa/view/RegistroUsuarioView.fxml", "Registro de Usuario");
    }
 
    @FXML
    public void handleRegresarLogin() {
        cambiarVista("/org/mkaa/view/InicioSesionView.fxml", "Inicio de Sesión");
    }
 
    @FXML
    public void handleRegistrarUsuario() {
        String usuario = txtNuevoUsuario.getText();
        String password = txtNuevaPassword.getText();
 
        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensajeRegistro.setText("Por favor complete todos los campos.");
            return;
        }
 

        String passwordHash = encriptarSHA256(password);
 

        boolean registrado = usuarioDAO.registrarUsuario(usuario, passwordHash, "admin");
 
        if (registrado) {
            lblMensajeRegistro.setText("¡Usuario registrado con éxito!");
            txtNuevoUsuario.clear();
            txtNuevaPassword.clear();
        } else {
            lblMensajeRegistro.setText("Error al registrar el usuario.");
        }
    }
     private String encriptarSHA256(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error al encriptar contraseña: " + e.getMessage());
            return texto;
        }
    }
 
    private void cambiarVista(String rutaFXML, String titulo) {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent raiz = cargador.load();
 
            Stage escenario = (Stage) (btnIniciarSesion != null && btnIniciarSesion.getScene() != null ? 
                    btnIniciarSesion.getScene().getWindow() : 
                    txtNuevoUsuario.getScene().getWindow());
 
            escenario.setScene(new Scene(raiz));
            escenario.setTitle(titulo);
            escenario.show();
        } catch (IOException e) {
            System.err.println("Error al cambiar de vista: " + e.getMessage());
        }
    }
 
    private void abrirDashBoard(Usuario usuario) {
        String rutaFXML = "/org/mkaa/view/MenuPrincipalDashboard.fxml";
        String tituloDashboard = "Panel de Administracion";
 
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent raiz = cargadorFXML.load();
 
            DashboardController controlado = cargadorFXML.getController();
            if (controlado != null) {
                controlado.iniciarUsuario(usuario);
            }
 
            Stage escenario = (Stage) btnIniciarSesion.getScene().getWindow();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle(tituloDashboard);
            escenario.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista: " + rutaFXML + " -> " + e.getMessage());
            e.printStackTrace();
            lblMensaje.setText("Error interno");
        }
    }
}
 