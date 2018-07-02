/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.stage.Stage;
import pacani.controller.*;
import pacani.modelo.*;


/**
 *
 * @author sebastian
 */
public class Pacani extends Application {
    
    private Stage stage;
    
    private Usuario user;
    
    public List<Cliente> clientes;
    public List<Pago> pagos;
    public List<Persona> personas;
    public List<Reserva> reservas;
    public List<Usuario> usuarios;
    
    private static Pacani instance;
    
    
    public Conexion n = new Conexion();
    
    public Pacani(){
        this.n.Conexion();
        instance = this;
        clientes = new ArrayList<>();
        pagos = new ArrayList<>();
        personas = new ArrayList<>();
        reservas = new ArrayList<>();
        usuarios = new ArrayList<>();
        inicializar();
    }
    
    public static Pacani getInstance() {
        return instance;
    }
    
    public void inicializar(){
        getPersonas();
        getClientes();
        getReservas();
    }
    
    
    private void getPersonas(){
        this.n.setExecuteQuery("SELECT * from Persona");
        try {
            while(this.n.getRs().next()){
                Persona per = new Persona();
                per.setRut(this.n.getRs().getString("rut"));
                per.setNombre(this.n.getRs().getString("nombre"));
                per.setApellido(this.n.getRs().getString("apellido"));
                per.setTelefono(this.n.getRs().getString("telefono"));
                per.setEmail(this.n.getRs().getString("email"));
                per.setF_nacimiento(this.n.getRs().getDate("f_nacimiento"));
                per.setFecha_creacion(this.n.getRs().getDate("fecha_creacion"));
                personas.add(per);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pacani.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Obtenida lista de "+personas.size()+" personas.");
    }
    
    private void getClientes(){
        this.n.setExecuteQuery("SELECT * from Cliente");
        try {
            while(this.n.getRs().next()){
                Cliente client = new Cliente();
                client.setPersona(Personas.buscarPersona(this.n.getRs().getString("rut")));
                client.setEstado(this.n.getRs().getInt("estado"));
                clientes.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pacani.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Obtenida lista de "+clientes.size()+" clientes.");
    }
    
    private void getReservas(){
        this.n.setExecuteQuery("SELECT * from Reserva");
        try {
            while(this.n.getRs().next()){
                Reserva res = new Reserva();
                res.setId_reserva(this.n.getRs().getInt("id_reserva"));
                res.setCliente(Clientes.buscarCliente(this.n.getRs().getString("cliente_rut")));
                res.setServicio_id(this.n.getRs().getShort("servicio_id"));
                res.setFecha_hora(this.n.getRs().getTimestamp("fecha_hora"));
                res.setPrecio_final(this.n.getRs().getDouble("precio_final"));
                res.setSaldo_pendiente(this.n.getRs().getDouble("saldo_pendiente"));
                res.setEstado(this.n.getRs().getInt("estado"));
                reservas.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pacani.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Obtenida lista de "+reservas.size()+" reservas.");
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

            user = new Usuario(Personas.getPersonaBD(userId), usuario, nivel);
            
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
            stage.setMinWidth(563);
            stage.setMinHeight(255);
            stage.setWidth(563);
            stage.setHeight(255);
            stage.setResizable(false);
        }
        

        
        return page;
    }
    
    
    
}
