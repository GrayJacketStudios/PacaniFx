/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controller;

import pacani.Pacani;

/**
 *
 * @author Keikruk
 */
public class Consultas {
    
    
    
    public static void SELECT(String query){
        try{
            Pacani.getInstance().n.setExecuteQuery(query);
        }catch(Exception e){
            reconnect();
            SELECT(query);
        }
        
    }
    
    public static void INSERT(String query){
        Pacani.getInstance().n.Conexion();
        try{
            Pacani.getInstance().n.setExecuteUpdate(query);
        }catch(Exception e){
            reconnect();
            INSERT(query);
        }
        
        
    }
    
    
    private static void reconnect(){
        Pacani.getInstance().n = new Conexion();
        Pacani.getInstance().n.Conexion();
    }
    
}
