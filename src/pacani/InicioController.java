/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;


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
    private TreeTableView<?> tablaReservas;
    @FXML
    private TreeTableColumn<?, ?> colNombre;
    @FXML
    private TreeTableColumn<?, ?> colServicio;
    @FXML
    private TreeTableColumn<?, ?> colFecha;
    @FXML
    private TreeTableColumn<?, ?> colHora;
    @FXML
    private TreeTableColumn<?, ?> colEstado;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }    
    
}
