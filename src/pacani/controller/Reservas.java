/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import pacani.Pacani;
import pacani.modelo.Pago;
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
    
    //Esta funcion devuelve la cantidad de servicios del mismo tipo que ha tenido una persona
    public static int totalServicio(String rut, int servicio){
        int count = 0;
        for(Reserva res: Pacani.getInstance().reservas){
            if(res.getCliente().getPersona().getRut().equals(rut) && res.getServicio_id() == servicio){
                count++;
            }
        }
        return count;
    }
    
    
    //Esta funcion verifica que no exista ningun tope horario con otra reserva comparando los datetimes de las otras reservas del mismo servicio junto con la solicitada.
    //Devuelve true si es que existe un tope.
    public static Boolean checkTope(Date solicitada, int servicio){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHH");
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for(Reserva res: Pacani.getInstance().reservas){
            if(res.getServicio_id() == servicio){
                long diff;
                diff = solicitada.getTime() - res.getFecha_hora().getTime();
                if(TimeUnit.MILLISECONDS.toMinutes(diff) < 60){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    //Devuelve el saldo pendiente que posee una reserva.
    public static double getSaldoPendiente(int idReserva){
        double saldoPendiente = 0;
        for(Pago pa: Pacani.getInstance().pagos){
            if(pa.getReserva().getId_reserva() == idReserva){
                saldoPendiente += (pa.getMonto()*-1);
            }
        }
        return saldoPendiente;
    }
    
    
}
