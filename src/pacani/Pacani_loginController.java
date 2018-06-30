/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pacani.controller.Conexion;

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
    
    @FXML
    private Label labelAdvertencia;
    
    Conexion n = new Conexion();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.n.Conexion();
        
    }    

    @FXML
    private void iniciaSesion(ActionEvent event)  {
        
        System.out.println("info: "+txt_User.getText()+" | "+txt_Pas.getText());
        this.n.setExecuteQuery("SELECT rut FROM Usuario WHERE username = '"+txt_User.getText()+"' AND password = '"+txt_Pas.getText()+"'");
        try {
            this.n.getRs().next();
        } catch (SQLException ex) {
            Logger.getLogger(Pacani_loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            System.out.println("Rut: "+this.n.getRs().getString(1));
            labelAdvertencia.setText("Iniciando sesión...");
            Pacani.getInstance().userLogging(this.n.getRs().getString(1), txt_User.getText(),this.n.getRs().getInt(1));
            
            
            
            
        }catch (Exception ex){
            labelAdvertencia.setText("Usuario o contraseña Incorrecta");
            System.out.println("error: "+ex);
        }
        
    }
    
}
