/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controller;

import pacani.Pacani;
import pacani.modelo.Reserva;

/**
 *
 * @author Keikruk
 */
public class Reservas {
    
    public static Reserva buscarReserva(int ID){
        for(Reserva res: Pacani.getInstance().reservas){
            if(res.getId_reserva() == ID){
                return res;
            }
        }
        return null;
    }
    
}
