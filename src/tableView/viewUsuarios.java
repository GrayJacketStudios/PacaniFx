/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableView;

/**
 *
 * @author Informatica
 */
public class viewUsuarios {
    private String nombre;
    private String rut;
    private String nivel;
    private String telefono;
    private String email;
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public viewUsuarios(String nombre, String rut, String nivel, String telefono, String email, String usuario) {
        this.nombre = nombre;
        this.rut = rut;
        this.nivel = nivel;
        this.telefono = telefono;
        this.email = email;
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public viewUsuarios() {
    }
    
}
