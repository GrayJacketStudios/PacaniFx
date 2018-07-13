/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pacani.Pacani;
import pacani.modelo.Persona;

/**
 *
 * @author Sebita
 */
public class Personas {

    
    public static Persona getPersonaBD(String rut){

        Pacani.getInstance().n.setExecuteQuery("SELECT * FROM Persona WHERE rut = '"+rut+"'");
        try {
            Pacani.getInstance().n.getRs().next();
        } catch (SQLException ex) {
            Logger.getLogger(Personas.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Persona p = new Persona(Pacani.getInstance().n.getRs().getNString("rut"),Pacani.getInstance().n.getRs().getNString("nombre"),Pacani.getInstance().n.getRs().getNString("apellido"),Pacani.getInstance().n.getRs().getDate("f_nacimiento"),Pacani.getInstance().n.getRs().getNString("telefono"),Pacani.getInstance().n.getRs().getNString("email"),Pacani.getInstance().n.getRs().getDate("fecha_creacion"));
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(Personas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static Persona buscarPersona(String rut){
        for(Persona perso: Pacani.getInstance().personas){
            if(perso.getRut().equals(rut)){
                return perso;
            }
        }
        return null;
    }
    
}
