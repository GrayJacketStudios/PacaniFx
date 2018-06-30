/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacani.modelo;

/**
 *
 * @author Keikruk
 */
public class Usuario {
    private String rut;
    private String username;
    private int nivel;

    public Usuario(String rut, String username, int nivel) {
        this.rut = rut;
        this.username = username;
        this.nivel = nivel;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
}
