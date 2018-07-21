/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controlador.fxml;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import pacani.Pacani;

/**
 * FXML Controller class
 *
 * @author sebastian
 */
public class HomeController implements Initializable {

    @FXML
    private ColumnConstraints grid_02;
    @FXML
    private JFXButton btn_inicio;
    @FXML
    private JFXButton btn_reservas;
    @FXML
    private JFXButton btn_calendario;
    @FXML
    private JFXButton btn_clientes;
    @FXML
    private JFXButton btn_pagos;
    @FXML
    private JFXButton btn_usuarios;
    @FXML
    private JFXButton btn_informacion;
    @FXML
    private JFXButton btn_salir;
    @FXML
    private ScrollPane fondo;

    /**
     * Initializes the controller class.
     */
    
    private static HomeController instance;
    
    public static HomeController getInstance(){
        return instance;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;
        cargarVista("inicio");
    }    

    @FXML
    private void click_Inicio(ActionEvent event) {
        cargarVista("inicio");
    }

    @FXML
    private void click_Reservas(ActionEvent event) {
        cargarVista("listaReservas");
    }


    @FXML
    private void click_Clientes(ActionEvent event) {
        cargarVista("listaClientes");
        
    }

    @FXML
    private void click_Pagos(ActionEvent event) {
        cargarVista("listaPagos");
    }

    @FXML
    private void click_Usuarios(ActionEvent event) {
        System.out.println("Nivel: over("+Pacani.getInstance().getLoggedUser().getNivel()+")");
        if(Pacani.getInstance().getLoggedUser().getNivel() >= 10){
            cargarVista("listaUsuarios");
        }
        
    }

    @FXML
    private void click_Info(ActionEvent event) {
        cargarVista("informacion");
    }

    @FXML
    private void click_Salir(ActionEvent event) {
        Pacani.getInstance().userLogout();
    }
    
    
    protected void cargarVista(String vista){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/pacani/"+vista+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fondo.setContent(root);
    }  
    
}
