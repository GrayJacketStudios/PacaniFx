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
public class Cliente {
    private Persona persona;
    private int estado;

    public Cliente() {
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Cliente(Persona persona, int estado) {
        this.persona = persona;
        this.estado = estado;
    }
}
