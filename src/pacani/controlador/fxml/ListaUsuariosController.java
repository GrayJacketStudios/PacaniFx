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
import pacani.modelo.Usuario;
import tableView.viewUsuarios;

/**
 * FXML Controller class
 *
 * @author Keikruk
 */
public class ListaUsuariosController implements Initializable {

    @FXML
    private TableColumn<viewUsuarios, String> trRut;
    @FXML
    private TableColumn<viewUsuarios, String> trUsuario;
    @FXML
    private TableColumn<viewUsuarios, String> trNivel;
    @FXML
    private TableColumn<viewUsuarios, String> trNombre;
    @FXML
    private TableColumn<viewUsuarios, String> trTelefono;
    @FXML
    private TableColumn<viewUsuarios, String> trEmail;
    @FXML
    private JFXButton btnNewUser;
    @FXML
    private JFXButton btnBuscarUser;
    @FXML
    private JFXTextField txtBuscarUser;
    @FXML
    private TableView<viewUsuarios> tableUsuarios;
    
    private ObservableList<viewUsuarios> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         data = FXCollections.observableArrayList();
        buildData();
        TableView<viewUsuarios> tableUsuarios = new TableView<viewUsuarios>();
        trRut.setCellValueFactory(
        new PropertyValueFactory<viewUsuarios, String>("rut"));
        trNombre.setCellValueFactory(
        new PropertyValueFactory<viewUsuarios, String>("nombre"));
        trEmail.setCellValueFactory(
        new PropertyValueFactory<viewUsuarios, String>("email"));
        trTelefono.setCellValueFactory(
        new PropertyValueFactory<viewUsuarios, String>("telefono"));
        trUsuario.setCellValueFactory(
        new PropertyValueFactory<viewUsuarios, String>("usuario"));
        trNivel.setCellValueFactory(
        new PropertyValueFactory<viewUsuarios, String>("nivel"));
    }    

    @FXML
    private void nuevoUsuario(ActionEvent event) {
        HomeController.getInstance().cargarVista("CrearUsuario");
    }

    @FXML
    private void buscarUsuario(ActionEvent event) {
    }
    
    
    
    private void buildData(){
        data.clear();
        viewUsuarios temp = null;
        try{
            for(Usuario user: Pacani.getInstance().usuarios){
                temp = new viewUsuarios();
                temp.setRut(user.getPersona().getRut());
                temp.setNombre(user.getPersona().getNombre()+" "+user.getPersona().getApellido());
                temp.setTelefono(user.getPersona().getTelefono()+"");
                temp.setEmail(user.getPersona().getEmail());
                temp.setUsuario(user.getUsername());
                temp.setNivel(user.getNivel()+"");
                data.add(temp);
            }
        }catch(Exception e){
                System.out.println("Error: "+e);
        }

        tableUsuarios.setItems(data);
        tableUsuarios.refresh();
    }
    
    
    
    
}
