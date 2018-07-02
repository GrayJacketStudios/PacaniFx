/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controller;

import pacani.Pacani;
import pacani.modelo.Cliente;

/**
 *
 * @author Sebita
 */
public class Clientes {
    
    public static Cliente buscarCliente(String rut){
        for(Cliente client: Pacani.getInstance().clientes){
            if(client.getPersona().getRut().equals(rut)){
                return client;
            }
        }
        return null;
    }
    
}
