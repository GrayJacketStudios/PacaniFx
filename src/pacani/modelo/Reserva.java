/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.modelo;

import java.util.Date;

/**
 *
 * @author Sebita
 */
public class Reserva {
    private int id_reserva;
    private short servicio_id;
    private Cliente cliente;
    private Date fecha_hora;
    private double precio_final;
    private double saldo_pendiente;
    private int estado;

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public short getServicio_id() {
        return servicio_id;
    }

    public void setServicio_id(short servicio_id) {
        this.servicio_id = servicio_id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(double precio_final) {
        this.precio_final = precio_final;
    }

    public double getSaldo_pendiente() {
        return saldo_pendiente;
    }

    public void setSaldo_pendiente(double saldo_pendiente) {
        this.saldo_pendiente = saldo_pendiente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Reserva() {
    }

    public Reserva(int id_reserva, short servicio_id, Cliente cliente, Date fecha_hora, double precio_final, double saldo_pendiente, int estado) {
        this.id_reserva = id_reserva;
        this.servicio_id = servicio_id;
        this.cliente = cliente;
        this.fecha_hora = fecha_hora;
        this.precio_final = precio_final;
        this.saldo_pendiente = saldo_pendiente;
        this.estado = estado;
    }
    
    
    
    }
    
