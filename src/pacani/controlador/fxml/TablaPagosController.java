/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pacani.Pacani;
import pacani.modelo.Pago;
import tableView.viewPagos;


/**
 * FXML Controller class
 *
 * @author Keikruk
 */
public class TablaPagosController implements Initializable {
    
    private int reservaID = 0;

    @FXML
    private TableView<viewPagos> tablaPagos;
    @FXML
    private TableColumn<viewPagos, String> rowFecha;
    @FXML
    private TableColumn<viewPagos, String> rowTipo;
    @FXML
    private TableColumn<viewPagos, String> rowDebito;
    @FXML
    private TableColumn<viewPagos, String> rowCredito;
    @FXML
    private TableColumn<viewPagos, String> rowNotas;
    @FXML
    private TableColumn<viewPagos, String> rowUsuario;
    
    protected ObservableList<viewPagos> data;
    @FXML
    private TableColumn<viewPagos, String>rowReserva;
    
    
    private static TablaPagosController instancia;
    
    
    
    public void setReservaId(int id_reserva){
        reservaID = id_reserva;
    }

    
    
    public static TablaPagosController getInstance(){
        return instancia;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instancia = this;
        data = FXCollections.observableArrayList();
        buildData();
        TableView<viewPagos> tablaPagos = new TableView<viewPagos>();
        rowFecha.setCellValueFactory(
        new PropertyValueFactory<viewPagos, String>("fecha"));
        rowCredito.setCellValueFactory(
        new PropertyValueFactory<viewPagos, String>("credito"));
        rowDebito.setCellValueFactory(
        new PropertyValueFactory<viewPagos, String>("debito"));
        rowNotas.setCellValueFactory(
        new PropertyValueFactory<viewPagos, String>("notas"));
        rowTipo.setCellValueFactory(
        new PropertyValueFactory<viewPagos, String>("tipo"));
        rowUsuario.setCellValueFactory(
        new PropertyValueFactory<viewPagos, String>("usuario"));
        rowReserva.setCellValueFactory(
        new PropertyValueFactory<viewPagos, String>("reserva"));
    }    
    
    
    
    
    private void buildData(){
        data.clear();
        viewPagos temp = null;
        try{
            for(Pago pago: Pacani.getInstance().pagos){
                if(reservaID != 0 && reservaID != pago.getReserva().getId_reserva()){
                    continue;
                }
                temp = new viewPagos();
                temp.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(pago.getFecha_pago()));
                
                switch(pago.getTipo()){
                    case 1:
                        temp.setTipo("Efectivo");
                        break;
                    case 2:
                            temp.setTipo("Tarjeta debito");
                        break;
                    case 3:
                        temp.setTipo("Tarjeta credito");
                        break;
                    case 0:
                        temp.setTipo("Cobro del sistema");
                }
                if(pago.getMonto() > 0){
                    temp.setCredito("0");
                    temp.setDebito(""+pago.getMonto());
                }
                else if(pago.getMonto() < 0){
                    temp.setDebito("0");
                    temp.setCredito((-1*pago.getMonto())+"");
                }
                
                temp.setNotas(pago.getComentario());
                temp.setReserva(pago.getReserva().getId_reserva()+"");
                temp.setUsuario(pago.getUsuario_rut().getUsername());
                
                
     
                data.add(temp);
            }
        }catch(Exception e){
                System.out.println("Error: "+e);
        }

        tablaPagos.setItems(data);
        tablaPagos.refresh();
    }
    
    
    
    
    
}
