/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controller;

import pacani.Pacani;
import pacani.modelo.Usuario;

/**
 *
 * @author Keikruk
 */
public class Usuarios {
    
    public static Usuario buscarUsuario(String rut){
        for(Usuario user: Pacani.getInstance().usuarios){
            if(user.getPersona().getRut().equals(rut)){
                return user;
            }
        }
        return null;
    }
    
    public static Usuario buscarUsername(String username){
        for(Usuario user: Pacani.getInstance().usuarios){
            if(username.equalsIgnoreCase(user.getUsername())){
                return user;
            }
        }
        return null;
    }
    
}
