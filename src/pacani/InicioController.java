/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;
import java.util.function.Function;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pacani.modelo.Reserva;
import tableView.ViewReservas;


/**
 * FXML Controller class
 *
 * @author Keikruk
 */
public class InicioController implements Initializable {

    @FXML
    private Label numMP;
    @FXML
    private Label numSP;
    @FXML
    private Label numTEP;
    @FXML
    private TableColumn<ViewReservas, String> trApellido;
    @FXML
    private TableColumn<ViewReservas, String> trNombre;
    @FXML
    private TableColumn<ViewReservas, String> trServicio;
    @FXML
    private TableColumn<ViewReservas, String> trFecha;
    @FXML
    private TableColumn<ViewReservas, String> trHora;
    @FXML
    private TableColumn<ViewReservas, String> trSaldo;
    @FXML
    private TableColumn<ViewReservas, String> trEstado;
    @FXML
    private TableView<ViewReservas> tablaReservas;
    @FXML
    private JFXDatePicker calendario;


    private DateTimeFormatter dateFormatter;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        calendario.setValue(LocalDate.now());
            
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ObservableList<ViewReservas> list=FXCollections.observableArrayList();
        data = FXCollections.observableArrayList();
        buildData();
        TableView<ViewReservas> tablaReservas = new TableView<ViewReservas>();
        trApellido.setCellValueFactory(
        new PropertyValueFactory<ViewReservas, String>("apellido"));
        trNombre.setCellValueFactory(
        new PropertyValueFactory<ViewReservas, String>("nombre"));
        trEstado.setCellValueFactory(
        new PropertyValueFactory<ViewReservas, String>("estado"));
        trFecha.setCellValueFactory(
        new PropertyValueFactory<ViewReservas, String>("fecha"));
        trHora.setCellValueFactory(
        new PropertyValueFactory<ViewReservas, String>("hora"));
        trSaldo.setCellValueFactory(
        new PropertyValueFactory<ViewReservas, String>("saldo"));
        trServicio.setCellValueFactory(
        new PropertyValueFactory<ViewReservas, String>("servicio"));
        
        tablaReservas.setPlaceholder(new Label("No hay reservas para la fecha indicada."));
        
        
        
        
        
        
    }



@FXML
public void clickItem(MouseEvent event)
{
    System.out.println("Click!");
    if (event.getClickCount() == 2) //Checking double click
    {
        try{
            System.out.println(tablaReservas.getSelectionModel().getSelectedItem().getNombre());
        }catch(Exception e){
            System.out.println("Error! "+e);
        }
        
    }
    
}



    private ObservableList<ViewReservas> data;
    @FXML
    private void buildData(){
        data.clear();
        
        
        try{
            Pacani.getInstance().reservas.stream().map(new FunctionImpl()).map((rv) -> {
                return rv;
            }).forEachOrdered((rv) -> {
                if(rv.getFecha().equals(dateFormatter.format(calendario.getValue()))){
                    data.add(rv);
                    
                }
                
            });
            tablaReservas.setItems(data);
            tablaReservas.refresh();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }

    private static class FunctionImpl implements Function<Reserva, ViewReservas> {

        public FunctionImpl() {
        }

        @Override
        public ViewReservas apply(Reserva res) {
            ViewReservas rv = new ViewReservas();
            rv.setApellido(res.getCliente().getPersona().getApellido());
            rv.setNombre(res.getCliente().getPersona().getNombre());
            switch (res.getServicio_id()) {
                case 1:
                    rv.setServicio("Sauna");
                    break;
                case 2:
                    rv.setServicio("Tratamiento estetico");
                    break;
                case 3:
                    rv.setServicio("Masaje");
                    break;
                default:
                    break;
            }
            rv.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(res.getFecha_hora()));
            rv.setHora(new SimpleDateFormat("HH:mm").format(res.getFecha_hora()));
            rv.setSaldo(Double.toString(res.getSaldo_pendiente()));
            switch (res.getEstado()) {
                case 1:
                    rv.setEstado("Pendiente");
                    break;
                case 2:
                    rv.setEstado("En curso");
                    break;
                case 3:
                    rv.setEstado("Cancelado");
                    break;
                case 4:
                    rv.setEstado("No show");
                    break;
                default:
                    break;
            }
            return rv;
        }
    }
    
}
