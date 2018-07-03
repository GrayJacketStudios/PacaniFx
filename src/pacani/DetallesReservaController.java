/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pacani.modelo.Reserva;

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
    private TableView<?> tablaPagos;
    @FXML
    private JFXButton btnNPago;
    @FXML
    private JFXButton btnATran;
    @FXML
    private JFXButton btnACargo;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private TableColumn<?, ?> rowFecha;
    @FXML
    private TableColumn<?, ?> rowTipo;
    @FXML
    private TableColumn<?, ?> rowDebito;
    @FXML
    private TableColumn<?, ?> rowCredito;
    @FXML
    private TableColumn<?, ?> rowNotas;
    @FXML
    private TableColumn<?, ?> rowUsuario;

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

    }
    
}
