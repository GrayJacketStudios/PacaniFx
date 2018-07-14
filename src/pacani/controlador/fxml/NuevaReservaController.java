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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pacani.Pacani;
import pacani.controller.Clientes;
import pacani.controller.Consultas;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        System.out.println(dateReserva.getValue()+"  "+timeReserva.getValue());
        Reserva res = new Reserva();
        res.setCliente(cli);
        res.setEstado(1);
        try {
            res.setFecha_hora(formatter.parse(dateReserva.getValue()+" "+timeReserva.getValue()));
        } catch (ParseException ex) {
            Logger.getLogger(NuevaReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch(cbbServicio.getValue()){
            case "Masajes":
                res.setServicio_id(Short.parseShort(1+""));
                break;
            case "Tratamientos esteticos":
                res.setServicio_id(Short.parseShort(2+""));
                break;
            case "Sauna":
                res.setServicio_id(Short.parseShort(3+""));
                break;
        }
        try{
            Consultas.INSERT("INSERT INTO `Reserva` (`cliente_rut`,`servicio_id`,`fecha_hora`,`precio_final`,`estado`,`saldo_pendiente`) VALUES ('"+res.getCliente().getPersona().getRut()+"','"+res.getServicio_id()+"','"+fmt.format(res.getFecha_hora())+"','0','1','0')");
            
            Pacani.getInstance().reservas.add(res);
        }catch(Exception e){
            System.out.println("Error: "+ e);
        }
        int valor = 0;
        switch(res.getServicio_id()){
            case 1:
                valor = 30000;
                break;
            case 2:
                valor = 45000;
                break;
            case 3:
                valor = 40000;
                break;
        }
        String id_reserva = null;
        try {
            id_reserva= (Pacani.getInstance().n.getRs().getInt(1))+"";
        } catch (SQLException ex) {
            Logger.getLogger(NuevaReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Pacani.getInstance().n.getRs().next();
        } catch (SQLException ex) {
            Logger.getLogger(NuevaReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            Consultas.INSERT("INSERT INTO Pago (id_reserva,monto,estado,comentario,usuario_rut,tipo) VALUES ('"+id_reserva+"', '"+(-1*valor)+"' , '1', 'Cobro automatico por servicio' , '"+Pacani.getInstance().getLoggedUser().getPersona().getRut()+"' ,'"+res.getServicio_id()+"')");
        }catch(Exception e){
            System.out.println("Error 2: "+e);
        }
        
        

        
    }

    @FXML
    private void btnNuevoCliente(ActionEvent event) {
        HomeController.getInstance().cargarVista("CrearCliente");
    }
    
}
