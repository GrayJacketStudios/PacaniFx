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
    private Persona persona;
    private String username;
    private int nivel;

    public Usuario(Persona persona, String username, int nivel) {
        this.persona = persona;
        this.username = username;
        this.nivel = nivel;
    }

    public Usuario() {
        
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
