
package pacani.modelo;

import java.util.Date;


public class Persona {
    private String rut;
    private String nombre;
    private String apellido;
    private Date f_nacimiento;
    private String telefono;
    private String email;
    private Date fecha_creacion;

    public Persona(String rut, String nombre, String apellido, Date f_nacimiento, String telefono, String email, Date fecha_creacion) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.f_nacimiento = f_nacimiento;
        this.telefono = telefono;
        this.email = email;
        this.fecha_creacion = fecha_creacion;
    }

    public Persona() {
    }


    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getF_nacimiento() {
        return f_nacimiento;
    }

    public void setF_nacimiento(Date f_nacimiento) {
        this.f_nacimiento = f_nacimiento;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
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
    
    
}
