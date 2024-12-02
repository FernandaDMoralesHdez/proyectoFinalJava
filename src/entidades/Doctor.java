//Fernanda Morales
//Subclase Doctor

package entidades;

import java.time.LocalDate;

public class Doctor extends Persona {
    private String especialidad;
    private String horario;

    public Doctor(String id, String nombre, String apellido, String telefono, String email, LocalDate fechaNacimiento, String especialidad, String horario) {
        super(id, nombre, apellido, telefono, email, fechaNacimiento);
        this.especialidad = especialidad;
        this.horario = horario;
    }


    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
}

