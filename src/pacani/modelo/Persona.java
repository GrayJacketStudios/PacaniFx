
package pacani.modelo;


public class Persona {
    private String rut;
    private String nombre;
    private String apellido;
    private String f_nacimiento;
    private String telefono;
    private String email;

    public Persona(String rut, String nombre, String apellido, String f_nacimiento, String telefono, String email) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.f_nacimiento = f_nacimiento;
        this.telefono = telefono;
        this.email = email;
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

    public String getF_nacimiento() {
        return f_nacimiento;
    }

    public void setF_nacimiento(String f_nacimiento) {
        this.f_nacimiento = f_nacimiento;
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
