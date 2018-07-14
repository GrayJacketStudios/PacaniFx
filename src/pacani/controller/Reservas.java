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
    
    public static int getLastReserva() {
        Consultas.SELECT("IDENT_CURRENT(`Reserva`)");
        try {
            Pacani.getInstance().n.getRs().next();
            return Pacani.getInstance().n.getRs().getInt("id_reserva");
        } catch (SQLException ex) {
            Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
