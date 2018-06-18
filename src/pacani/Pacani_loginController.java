/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author sebastian
 */
public class Pacani_loginController implements Initializable {

    @FXML
    private JFXTextField txt_User;
    @FXML
    private JFXPasswordField txt_Pas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciaSesion(ActionEvent event) {
        System.out.println("info: "+txt_User.getText()+" | "+txt_Pas.getText());
    }
    
}
