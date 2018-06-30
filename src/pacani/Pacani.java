/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pacani.controller.Conexion;
import pacani.modelo.Usuario;

/**
 *
 * @author sebastian
 */
public class Pacani extends Application {
    
    private ImageView loginbg;
    private Stage stage;
    
    private Usuario user;
    
    private static Pacani instance;
    
    public Pacani(){
        instance = this;
    }
    
    public static Pacani getInstance() {
        return instance;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        

        try {
            stage = primaryStage;
            gotoHome();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Pacani.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public Usuario getLoggedUser() {
        return user;
    }

    public boolean userLogging(String userId, String usuario, int nivel){
            user = new Usuario(userId, usuario, nivel);
            gotoHome();
            return true;

    }

    public void userLogout(){
        user = null;
        gotoLogin();
    }
    
    private void gotoLogin() {
        try {
            replaceSceneContent("pacani_login.fxml");
        } catch (Exception ex) {
            Logger.getLogger(Pacani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void gotoHome() {
        try {
            replaceSceneContent("home.fxml");
        } catch (Exception ex) {
            Logger.getLogger(Pacani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(Pacani.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 563, 240);
            
            stage.setScene(scene);
            

        } else {
            stage.getScene().setRoot(page);
        }
        if(fxml.equals("home.fxml")){
            stage.setWidth(916);
            stage.setMinWidth(780);
            stage.setMinHeight(445);
            stage.setHeight(445);
            stage.setResizable(true);
        }
        else if(fxml.equals("pacani_login.fxml")){
            stage.setWidth(563);
            stage.setHeight(255);
            stage.setResizable(false);
        }
        

        
        return page;
    }
    
    
    
}
