/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pacani.Pacani;
import pacani.controller.Conexion;
import pacani.controller.Consultas;

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
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    

    @FXML
    private void iniciaSesion(ActionEvent event)  {
        
        try{
            System.out.println("info: "+txt_User.getText()+" | "+txt_Pas.getText());
            Consultas.SELECT("SELECT * FROM Usuario  WHERE username = '"+txt_User.getText()+"' AND password = '"+txt_Pas.getText()+"'");

            try {
                Pacani.getInstance().n.getRs().next();
            } catch (SQLException ex) {
                reTry(event);
            }
            try{
                System.out.println("Rut: "+Pacani.getInstance().n.getRs().getString(1));
                labelAdvertencia.setText("Iniciando sesión...");
                
                Pacani.getInstance().userLogging(Pacani.getInstance().n.getRs().getString("rut"), txt_User.getText(),Pacani.getInstance().n.getRs().getInt("nivel"));




            }catch (Exception ex){
                labelAdvertencia.setText("Usuario o contraseña Incorrecta");
            }
        }catch(RuntimeException e){
            reTry(event);
        }
        
    }
    private void reTry(ActionEvent event){
            Pacani.getInstance().n = new Conexion();
            Pacani.getInstance().n.Conexion();
            iniciaSesion(event);
    }

    @FXML
    private void onEnter(ActionEvent event) {
        iniciaSesion(event);
    }
    
}
