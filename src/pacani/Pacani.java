/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author sebastian
 */
public class Pacani extends Application {
    
    private ImageView loginbg;
    
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        

        
        Parent root = FXMLLoader.load(Pacani.class.getResource("pacani_login.fxml"));
        
        Scene scene = new Scene(root, 563, 240);
        
        primaryStage.setTitle("Iniciar sesión");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    
}
