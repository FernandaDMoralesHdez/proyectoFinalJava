// Fernanda Morales
// Clase Cita

package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Citas implements Serializable {
    private int idCita;
    private String  fecha;
    private String hora;
    private String motivo;
    private String doctor;
    private String paciente;

    // Constructor
    public Citas(int idCita, String fecha, String hora, String motivo, String doctor, String paciente) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    // Getters
    public int getIdCita() { return idCita; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getMotivo() { return motivo; }
    public String getDoctor() { return doctor; }
    public String getPaciente() { return paciente; }

    // Metodo para imprimir la información de la cita
    @Override
    public String toString() {
        DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Retornar una cadena con toda la información de la cita
        return "ID Cita: " + idCita + "\n" +
                "Fecha: " + fecha + "\n" +
                "Hora: " + hora + "\n" +
                "Motivo: " + motivo + "\n" +
                "Doctor: " + doctor + "\n" +
                "Paciente: " + paciente;
    }

}


