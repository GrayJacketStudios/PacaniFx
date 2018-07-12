/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import pacani.Pacani;
import pacani.modelo.Persona;

/**
 * FXML Controller class
 *
 * @author egamb
 */
public class CrearUsuarioController implements Initializable {

    @FXML
    private JFXButton btnCUser;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtApellido;
    @FXML
    private JFXTextField txtRut;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtUser;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXDatePicker dateNacimiento;
    
    
    
    private DateTimeFormatter dateFormatter;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }    

    @FXML
    private void volver(ActionEvent event) {
    }

    @FXML
    private void crearUsuario(ActionEvent event) {
        Persona per = new Persona(txtRut.getText(),txtNombre.getText(),txtApellido.getText(),dateFormatter.format(dateNacimiento.getValue()),txtTelefono.getText(),txtCorreo.getText());
        Pacani.getInstance().n.setExecuteQuery("INSERT INTO ");
    }
    
}
