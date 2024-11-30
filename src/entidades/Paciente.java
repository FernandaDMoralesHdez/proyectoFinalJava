//Fernanda Morales
//Subclase Paciente

package entidades;

import java.io.Serializable;
import java.time.LocalDate;

public class Paciente extends Persona implements Serializable {
    private String historialMedico;

    //Constructor con parámetros
    public Paciente(String id, String nombre, String apellido, String telefono, String email, LocalDate fechaNacimiento, String historialMedico) {
        super(id, nombre, apellido, telefono, email, fechaNacimiento); // Llama al constructor de la clase Persona
        this.historialMedico = historialMedico;
    }

    //Constructor vacío
    public Paciente() {
        super(); // Llama al constructor vacío de la clase Persona
        this.historialMedico = ""; // Inicializa el historial médico con una cadena vacía
    }


    public String getHistorialMedico() { return historialMedico; }
    public void setHistorialMedico(String historialMedico) { this.historialMedico = historialMedico; }
}


