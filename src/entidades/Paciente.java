//Fernanda Morales
//Subclase Paciente

package entidades;

import java.io.Serializable;
import java.time.LocalDate;

public class Paciente extends Persona implements Serializable {
    private String historialMedico;

    public Paciente(String id, String nombre, String apellido, String telefono, String email, LocalDate fechaNacimiento, int idPaciente, String historialMedico) {
        super(id,nombre, apellido, telefono, email, fechaNacimiento);
        this.historialMedico = historialMedico;
    }


    public String getHistorialMedico() { return historialMedico; }
    public void setHistorialMedico(String historialMedico) { this.historialMedico = historialMedico; }
}


