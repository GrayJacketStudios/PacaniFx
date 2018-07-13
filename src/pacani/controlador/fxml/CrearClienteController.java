/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import pacani.controller.Clientes;
import pacani.controller.Consultas;
import pacani.controller.Personas;
import pacani.modelo.Cliente;
import pacani.modelo.Persona;

/**
 * FXML Controller class
 *
 * @author egamb
 */
public class CrearClienteController implements Initializable {

    @FXML
    private JFXButton btnCCliente;
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
        HomeController.getInstance().cargarVista("listaClientes");
    }

    public void crearCliente(ActionEvent event) {
        Persona per = null;
        per = Personas.buscarPersona(txtRut.getText());
        if(per == null){
            per = new Persona(txtRut.getText(),txtNombre.getText(),txtApellido.getText(),java.sql.Date.valueOf(dateNacimiento.getValue()),txtTelefono.getText(),txtCorreo.getText(),Calendar.getInstance().getTime());
            Pacani.getInstance().personas.add(per);
            Consultas.INSERT("INSERT INTO `Persona` (`rut`, `nombre`, `apellido`, `f_nacimiento`, `telefono`, `email`) VALUES ('"+per.getRut()+"','"+per.getNombre()+"','"+per.getApellido()+"','"+dateFormatter.format(per.getF_nacimiento())+"','"+per.getTelefono()+"','"+per.getEmail()+"')");
            
        }
        
        Cliente user = Clientes.buscarCliente(txtRut.getText());
        if(user != null){
            System.out.println("Ya hay un cliente registrado bajo este rut.");
            lblMsg.setText("Ya hay un cliente bajo este rut.");
            return;
        }

        try{
            user = new Cliente(per,1);
            Pacani.getInstance().clientes.add(user);
            Consultas.INSERT("INSERT INTO `Cliente` (`rut`, `estado`, `creacion`) VALUES ('"+user.getPersona().getRut()+"','1','1')");
            System.out.println("Cliente añadido.");
            lblMsg.setText("Cliente añadido correctamente.");
        }catch(Exception e){
            lblMsg.setText("Ocurrio un error al intentar agregar el cliente.");
        }
        
        
        
        
    }

    @FXML
    private void crearUsuario(ActionEvent event) {
    }
    
}
