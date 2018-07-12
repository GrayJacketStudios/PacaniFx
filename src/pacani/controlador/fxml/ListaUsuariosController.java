/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author Keikruk
 */
public class ListaUsuariosController implements Initializable {

    @FXML
    private TableColumn<?, ?> trRut;
    @FXML
    private TableColumn<?, ?> trUsuario;
    @FXML
    private TableColumn<?, ?> trNivel;
    @FXML
    private TableColumn<?, ?> trNombre;
    @FXML
    private TableColumn<?, ?> trTelefono;
    @FXML
    private TableColumn<?, ?> trEmail;
    @FXML
    private JFXButton btnNewUser;
    @FXML
    private JFXButton btnBuscarUser;
    @FXML
    private JFXTextField txtBuscarUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void nuevoUsuario(ActionEvent event) {
        HomeController.getInstance().cargarVista("CrearUsuario");
    }

    @FXML
    private void buscarUsuario(ActionEvent event) {
    }
    
}
