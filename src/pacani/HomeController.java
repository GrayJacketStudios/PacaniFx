/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;

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
    private Pane fondo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_Inicio(ActionEvent event) {
    }

    @FXML
    private void click_Reservas(ActionEvent event) {
    }

    @FXML
    private void click_Calendario(ActionEvent event) {
    }

    @FXML
    private void click_Clientes(ActionEvent event) {
    }

    @FXML
    private void click_Pagos(ActionEvent event) {
    }

    @FXML
    private void click_Usuarios(ActionEvent event) {
    }

    @FXML
    private void click_Info(ActionEvent event) {
    }

    @FXML
    private void click_Salir(ActionEvent event) {
    }
    
}
