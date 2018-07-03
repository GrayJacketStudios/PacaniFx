/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.modelo;

/**
 *
 * @author Sebita
 */
public class Pago {
    private int id_pago;
    private Reserva reserva;
    private int monto;
    private int estado;
    private String comentario;
    private int usuario_rut;
    private int tipo;
    private int tarjeta_id;

    public Pago() {
    }
    

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getUsuario_rut() {
        return usuario_rut;
    }

    public void setUsuario_rut(int usuario_rut) {
        this.usuario_rut = usuario_rut;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTarjeta_id() {
        return tarjeta_id;
    }

    public void setTarjeta_id(int tarjeta_id) {
        this.tarjeta_id = tarjeta_id;
    }

    public Pago(int id_pago, Reserva reserva, int monto, int estado, String comentario, int usuario_rut, int tipo, int tarjeta_id) {
        this.id_pago = id_pago;
        this.reserva = reserva;
        this.monto = monto;
        this.estado = estado;
        this.comentario = comentario;
        this.usuario_rut = usuario_rut;
        this.tipo = tipo;
        this.tarjeta_id = tarjeta_id;
    }
}
