//Fernanda Morales
//Superclase Persona, de la cual se heredan atributos a las subclases Paciente y Doctor

package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Persona implements Serializable {
    //Atributos privados
    private String id, nombre, apellido, telefono, email;
    private LocalDate fechaNacimiento;

    //Constructores
    public Persona(String id,String nombre, String apellido, String telefono, String email, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

   //Metodo para obtener nombre completo
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
    //Metodo para obtener edad del paciente
    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    //Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}

