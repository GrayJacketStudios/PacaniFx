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
import javafx.scene.input.InputMethodEvent;
import pacani.Pacani;
import pacani.controller.Clientes;
import pacani.controller.Consultas;
import pacani.controller.Reservas;
import pacani.modelo.Cliente;
import pacani.modelo.Pago;
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
    
    
    private int servicioId = 3;
    private int precio = 30000;

    
    private String[] servicios = new String[] {"Masajes", "Tratamientos esteticos", "Sauna"};
    @FXML
    private Label lblPrecio;
    @FXML
    private Label lblDescuento;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbbServicio.setItems(FXCollections.observableArrayList(servicios));
        lblPrecio.setText(precio+"");
        
    }    

    @FXML
    private void volver(ActionEvent event) {
        HomeController.getInstance().cargarVista("inicio");
    }

    @FXML
    private void crearReserva(ActionEvent event) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Cliente cli = null;
        cli = Clientes.buscarCliente(txtRut.getText());
        if(cli == null){
            System.out.println("No hay clientes con ese rut.");
            lblMsg.setText("No hay clientes con ese rut.");
            return;
        }
        if(txtRut.getText().equals("") || dateReserva.getValue() == null || timeReserva.getValue() == null ){
            System.out.println("Se debe ingresar todos los datos.");
            lblMsg.setText("Se debe ingresar todos los datos.");
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
        
        if(Reservas.checkTope(res.getFecha_hora(), servicioId)){
            lblMsg.setText("Ya hay una reserva de ese tipo a esa hora.");
            return;
        }
        
        res.setServicio_id((short) servicioId);
        try{
            Consultas.INSERT("INSERT INTO `Reserva` (`cliente_rut`,`servicio_id`,`fecha_hora`,`precio_final`,`estado`,`saldo_pendiente`) VALUES ('"+res.getCliente().getPersona().getRut()+"','"+servicioId+"','"+fmt.format(res.getFecha_hora())+"','"+precio+"','1','0')");
            
            Pacani.getInstance().reservas.add(res);
        }catch(Exception e){
            System.out.println("Error: "+ e);
        }
        int id_reserva = 0;
        try {
            Pacani.getInstance().n.getRs().next();
            
            id_reserva= (Pacani.getInstance().n.getRs().getInt(1));
            Pacani.getInstance().reservas.get(Pacani.getInstance().reservas.size() - 1).setId_reserva(id_reserva);
        } catch (SQLException ex) {
            Logger.getLogger(NuevaReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Pacani.getInstance().n.getRs().next();
        } catch (SQLException ex) {
            Logger.getLogger(NuevaReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            Consultas.INSERT("INSERT INTO Pago (id_reserva,monto,estado,comentario,usuario_rut,tipo) VALUES ('"+id_reserva+"', '"+(-1*precio)+"' , '1', 'Cobro automatico por servicio' , '"+Pacani.getInstance().getLoggedUser().getPersona().getRut()+"' ,'"+res.getServicio_id()+"')");
            Pago pago = new Pago();
            Pacani.getInstance().n.getRs().next();
            pago.setId_pago((Pacani.getInstance().n.getRs().getInt(1)));
            pago.setMonto(-1*precio);
            pago.setComentario("Cobro automatico por servicio");
            pago.setFecha_pago(java.sql.Date.valueOf(dateReserva.getValue()));
            Pacani.getInstance().pagos.add(pago);
            HomeController.getInstance().cargarVista("inicio");
        
        }catch(Exception e){
            System.out.println("Error 2: "+e);
        }
        
        

        
    }

    @FXML
    private void btnNuevoCliente(ActionEvent event) {
        HomeController.getInstance().cargarVista("CrearCliente");
    }

    @FXML
    private void cambiaTipo(ActionEvent event) {
        System.out.println(cbbServicio.getValue());
        switch(cbbServicio.getValue()){
            case "Masajes":
                servicioId = 3;
                precio = 30000;
                break;
            case "Tratamientos esteticos":
                servicioId = 2;
                precio = 55000;
                break;
            case "Sauna":
                servicioId = 1;
                precio = 45000;
                break;
        }
        
        lblPrecio.setText(precio+"");
        checkDescuento();
    }

    @FXML
    private void checkDescuento(InputMethodEvent event) {
        
        int cantDescuentos = Reservas.totalServicio(txtRut.getText(), servicioId);
        System.out.println(cantDescuentos+"");
        if((cantDescuentos%3) == 0 && cantDescuentos != 0){
            lblDescuento.setText("¡Descuento del 10% aplicado!");
            lblPrecio.setText(precio+"-15% = $"+(int) (precio*0.90));
            precio = (int) (precio*0.90);
        }
        else{
            lblDescuento.setText("");
        }
    }

    private void checkDescuento(){
        int cantDescuentos = Reservas.totalServicio(txtRut.getText(), servicioId);
        System.out.println(cantDescuentos+"");
        if((cantDescuentos%3) == 0 && cantDescuentos != 0 ){
            lblDescuento.setText("¡Descuento del 10% aplicado!");
            lblPrecio.setText(precio+"-15% = $"+(int) (precio*0.90));
            precio = (int) (precio*0.90);
        }
        else{
            lblDescuento.setText("");
        }
    }
    
}
