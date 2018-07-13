/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pacani.Pacani;
import pacani.modelo.Cliente;
import tableView.viewClientes;

/**
 * FXML Controller class
 *
 * @author Keikruk
 */
public class ListaClientesController implements Initializable {

    @FXML
    private TableColumn<viewClientes, String> trRut;
    @FXML
    private TableColumn<viewClientes, String> trNombre;
    @FXML
    private TableColumn<viewClientes, String> trTelefono;
    @FXML
    private TableColumn<viewClientes, String> trEmail;
    @FXML
    private TableColumn<viewClientes, String> trEstado;
    @FXML
    private JFXButton btnNewCliente;
    @FXML
    private JFXButton btnBuscarCliente;
    @FXML
    private JFXTextField txtBuscarCliente;
    @FXML
    private TableView<viewClientes> tablaClientes;

    private ObservableList<viewClientes> data;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        buildData();
        TableView<viewClientes> tablaClientes = new TableView<viewClientes>();
        trRut.setCellValueFactory(
        new PropertyValueFactory<viewClientes, String>("rut"));
        trNombre.setCellValueFactory(
        new PropertyValueFactory<viewClientes, String>("nombre"));
        trEmail.setCellValueFactory(
        new PropertyValueFactory<viewClientes, String>("email"));
        trTelefono.setCellValueFactory(
        new PropertyValueFactory<viewClientes, String>("telefono"));
        trEstado.setCellValueFactory(
        new PropertyValueFactory<viewClientes, String>("estado"));
    }    

    @FXML
    private void nuevoCliente(ActionEvent event) {
        HomeController.getInstance().cargarVista("CrearCliente");
    }

    @FXML
    private void buscarCliente(ActionEvent event) {
        if(!"".equals(txtBuscarCliente.getText())){
            
            
            
            
        }
    }
    
    
    
    private void buildData(){
        data.clear();
        viewClientes temp = null;
        try{
            for(Cliente cli: Pacani.getInstance().clientes){
                temp = new viewClientes();
                temp.setRut(cli.getPersona().getRut());
                temp.setNombre(cli.getPersona().getNombre()+" "+cli.getPersona().getApellido());
                temp.setTelefono(cli.getPersona().getTelefono()+"");
                temp.setEmail(cli.getPersona().getEmail());
                switch(cli.getEstado()){
                    case 1:
                        temp.setEstado("Activo");
                        break;
                    case 2:
                        temp.setEstado("De baja");
                        break;
                    case 3:
                        temp.setEstado("Baneado");
                        break;
                }
                data.add(temp);
            }
        }catch(Exception e){
                System.out.println("Error: "+e);
        }

        tablaClientes.setItems(data);
        tablaClientes.refresh();
    }
}
