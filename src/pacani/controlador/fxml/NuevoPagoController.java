/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pacani.Pacani;
import pacani.controller.Consultas;
import pacani.controller.Reservas;
import pacani.modelo.Pago;
import pacani.modelo.Reserva;

/**
 * FXML Controller class
 *
 * @author Informatica
 */
public class NuevoPagoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    private Reserva reserva = null;
    
    
    private static NuevoPagoController instance = null;
    @FXML
    private Label lblReserva;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblServicio;
    @FXML
    private Label lblSaldoP;
    @FXML
    private JFXComboBox<String> cbbPago;
    @FXML
    private JFXTextField txtPago;

    
     private String[] metodo = new String[] {"Efectivo", "Tarjeta de debito", "Tarjeta de credito"};
    @FXML
    private Label lblMsg;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;   
        cbbPago.setItems(FXCollections.observableArrayList(metodo));
    }     
    
    
    
    
    public void buildData(){
        lblReserva.setText(reserva.getId_reserva()+"");
        lblNombre.setText(reserva.getCliente().getPersona().getNombre()+" "+reserva.getCliente().getPersona().getApellido());
        lblSaldoP.setText((int) Reservas.getSaldoPendiente(reserva.getId_reserva())+"");
        txtPago.setText(lblSaldoP.getText());
        
        switch(reserva.getServicio_id()){
            case 1:
                lblServicio.setText("Sauna");
                break;
            case 2:
                lblServicio.setText("Tratamiento estetico");
                break;
            case 3:
                lblServicio.setText("Masaje");
                break;
        }
        
        
    }
    
    
    
    public void setReserva(Reserva res){
        reserva = res;
    }
    
    public static NuevoPagoController getInstance(){
        return instance;
    }

    @FXML
    private void onPagar(ActionEvent event) {
        //Comprobamos de que no se haya cambiado el valor a 0 o a negativo.
        if(Integer.parseInt(txtPago.getText()) <= 0){
            lblMsg.setText("El pago debe ser mayor que 0 pesos.");
            return;
        }

        if(cbbPago.getValue() == null){
            lblMsg.setText("Debes ingresar un metodo de pago.");
            return;
        }
        
        int metodo = 0;
        switch(cbbPago.getValue()){
            case "Efectivo":
                metodo = 1;
                break;
            case "Tarjeta de credito":
                metodo = 3;
                break;
            case "Tarjeta de debito":
                metodo = 2;
                break;
        }
        
        Pago pago = new Pago();
        pago.setReserva(reserva);
        pago.setEstado(1);
        pago.setFecha_pago(Calendar.getInstance().getTime());
        pago.setMonto(Integer.parseInt(txtPago.getText()));
        pago.setTipo(metodo);
        pago.setComentario("Pago añadido a la reserva.");
        pago.setUsuario_rut(Pacani.getInstance().getLoggedUser().getPersona().getRut());
        try{
            Pacani.getInstance().pagos.add(pago);
            Consultas.INSERT("INSERT INTO `Pago` (`id_reserva`,`monto`,`estado`,`comentario`,`usuario_rut`,`tipo`) VALUES ('"+pago.getReserva().getId_reserva()+"','"+pago.getMonto()+"','1','Pago añadido a la reserva','"+pago.getUsuario_rut().getPersona().getRut()+"','"+metodo+"')");
        }catch(Exception e){
            System.out.println("Error en NuevoPago: "+e);
            lblMsg.setText("Se presento un error.");
            return;
        }
                HomeController.getInstance().cargarVista("detallesReserva");
        DetallesReservaController.getInstance().setReserva(reserva);
        DetallesReservaController.getInstance().iniciar();
        
        
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        HomeController.getInstance().cargarVista("detallesReserva");
        DetallesReservaController.getInstance().setReserva(reserva);
        DetallesReservaController.getInstance().iniciar();
    }
    
    
    
}
