/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pacani.controller.Clientes;
import pacani.controller.Reservas;
import pacani.modelo.Cliente;
import pacani.modelo.Reserva;

/**
 * FXML Controller class
 *
 * @author Keikruk
 */
public class NuevaReservaController implements Initializable {

    @FXML
    private JFXButton btnCReserva;
    @FXML
    private Label lblMsg;
    @FXML
    private JFXTextField txtRut;
    @FXML
    private JFXDatePicker dateReserva;
    @FXML
    private JFXComboBox<String> cbbServicio;
    @FXML
    private JFXTimePicker timeReserva;

    
    private String[] servicios = new String[] {"Masajes", "Tratamientos esteticos", "Sauna"};
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbbServicio.setItems(FXCollections.observableArrayList(servicios));
        
    }    

    @FXML
    private void volver(ActionEvent event) {
    }

    @FXML
    private void crearReserva(ActionEvent event) {
        Cliente cli = null;
        cli = Clientes.buscarCliente(txtRut.getText());
        if(cli == null){
            System.out.println("No hay clientes con ese rut.");
            return;
        }
        if(txtRut.getText().equals("") || dateReserva.getValue().equals("") || timeReserva.getValue().equals("") ){
            System.out.println("Se debe ingresar todos los datos.");
            return;
        }
        Reserva res = new Reserva();
        res.setCliente(cli);
        res.setEstado(1);

        
    }

    @FXML
    private void btnNuevoCliente(ActionEvent event) {
        HomeController.getInstance().cargarVista("CrearCliente");
    }
    
}
