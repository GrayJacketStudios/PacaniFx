/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pacani.modelo.Reserva;
import pacani.Pacani;
import pacani.controller.Reservas;
import pacani.modelo.Pago;

/**
 * FXML Controller class
 *
 * @author Keikruk
 */
public class DetallesReservaController implements Initializable {
    
    private Reserva reserva = null;
    private static DetallesReservaController instance;

    @FXML
    private Label txtNombreCliente;
    @FXML
    private Label txtTelefono;
    @FXML
    private Label txtCorreo;
    @FXML
    private Label txtEdad;
    @FXML
    private Label txtFCreacion;
    @FXML
    private Label txtReserva;
    @FXML
    private Label txtServicio;
    @FXML
    private Label txtFecha;
    @FXML
    private Label txtHora;
    @FXML
    private JFXComboBox<String> cbEstado;
    @FXML
    private Label txtSaldoP;
    @FXML
    private JFXButton btnNPago;
    @FXML
    private JFXButton btnATran;
    @FXML
    private JFXButton btnACargo;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private HBox boxPagos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;
    }

    public static DetallesReservaController getInstance(){
        return instance;
    }
    
    public void setReserva(Reserva data){
        reserva = data;
    }
    
    public void iniciar(){
        System.out.println("id: "+reserva.getId_reserva());
        Date fechaNacimiento = reserva.getCliente().getPersona().getF_nacimiento();
        LocalDate FN = Instant.ofEpochMilli(fechaNacimiento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        txtNombreCliente.setText(reserva.getCliente().getPersona().getNombre()+" "+reserva.getCliente().getPersona().getApellido());
        txtCorreo.setText(reserva.getCliente().getPersona().getEmail());
        txtTelefono.setText(reserva.getCliente().getPersona().getTelefono());
        txtFCreacion.setText(new SimpleDateFormat("dd/MM/yyyy").format(reserva.getCliente().getPersona().getFecha_creacion())+"");
        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(reserva.getFecha_hora()));
        txtHora.setText(new SimpleDateFormat("HH:mm").format(reserva.getFecha_hora()));
        txtReserva.setText(reserva.getId_reserva()+"");
        txtEdad.setText((Period.between(FN, LocalDate.now()).getYears())+" Años");
        switch(reserva.getServicio_id()){
            case 1:
                txtServicio.setText("Sauna");
                break;
            case 2:
                txtServicio.setText("Tratamiento estetico");
                break;
            case 3:
                txtServicio.setText("Masaje");
                break;
        }
        
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/pacani/tablaPagos.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        boxPagos.getChildren().add(root);
        TablaPagosController.getInstance().setReservaId(reserva.getId_reserva());
        TablaPagosController.getInstance().buildData();
        
        txtSaldoP.setText(Reservas.getSaldoPendiente(reserva.getId_reserva())+"");
        

    }

    @FXML
    private void clickVolver(ActionEvent event) {
        HomeController.getInstance().cargarVista("inicio");
    }

    @FXML
    private void nuevoPago(ActionEvent event) {
        
        HomeController.getInstance().cargarVista("nuevoPago");
        NuevoPagoController.getInstance().setReserva(reserva);
        NuevoPagoController.getInstance().buildData();
    }
    
}
