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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pacani.Pacani;
import pacani.controller.Consultas;
import pacani.controller.Personas;
import pacani.controller.Usuarios;
import pacani.modelo.Persona;
import pacani.modelo.Usuario;

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
    
    
    
    private SimpleDateFormat dateFormatter;
    @FXML
    private Label lblMsg;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dateFormatter = new SimpleDateFormat("YYYY-MM-dd");
    }    

    @FXML
    private void volver(ActionEvent event) {
        HomeController.getInstance().cargarVista("listaUsuarios");
    }

    @FXML
    private void crearUsuario(ActionEvent event) {
        Persona per = null;
        per = Personas.buscarPersona(txtRut.getText());
        if(per == null){
            per = new Persona(txtRut.getText(),txtNombre.getText(),txtApellido.getText(),java.sql.Date.valueOf(dateNacimiento.getValue()),txtTelefono.getText(),txtCorreo.getText(),Calendar.getInstance().getTime());
            Pacani.getInstance().personas.add(per);
            Consultas.INSERT("INSERT INTO `Persona` (`rut`, `nombre`, `apellido`, `f_nacimiento`, `telefono`, `email`) VALUES ('"+per.getRut()+"','"+per.getNombre()+"','"+per.getApellido()+"','"+dateFormatter.format(per.getF_nacimiento())+"','"+per.getTelefono()+"','"+per.getEmail()+"')");
            
        }
        
        Usuario user = Usuarios.buscarUsuario(txtRut.getText());
        if(user != null){
            System.out.println("Ya hay un usuario registrado bajo este rut.");
            lblMsg.setText("Ya hay un usuario bajo este rut.");
            return;
        }
        user = Usuarios.buscarUsername(txtUser.getText());
        if(user != null){
            System.out.println("Ya hay un usuario registrado bajo este nombre de usuario.");
            lblMsg.setText("Ya hay un usuario bajo este username.");
            return;
        }
        try{
            user = new Usuario(per,txtUser.getText(),0);
            Pacani.getInstance().usuarios.add(user);
            Consultas.INSERT("INSERT INTO `Usuario` (`rut`, `username`, `password`, `nivel`) VALUES ('"+user.getPersona().getRut()+"','"+user.getUsername()+"','"+txtPass.getText()+"','0')");
            System.out.println("Usuario añadido.");
            lblMsg.setText("Usuario añadido correctamente.");
        }catch(Exception e){
            lblMsg.setText("Ocurrio un error al intentar agregar el usuario.");
        }
        
        
        
        
    }
    
}
