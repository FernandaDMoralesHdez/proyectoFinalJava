// Fernanda Morales
// Clase Cita

package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import entidades.Citas;
import entidades.Doctor;
import entidades.Paciente;


public class Citas implements Serializable {
    //Atributos privados
    private int idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private String doctor;
    private String paciente;

    //Constructor
    public Citas(int idCita, LocalDate fecha, LocalTime hora, String motivo, String doctor, String paciente) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    //Métodos para asignar doctor y paciente
    public void asignarDoctor(Doctor doctor) {
        this.doctor = doctor.getNombreCompleto();
    }

    public void asignarPaciente(Paciente paciente) {
        this.paciente = paciente.getNombreCompleto();
    }

    //Getters y setters
    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDoctor() { return doctor; }
    public void setDoctor(String doctor) { this.doctor = doctor; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    //Metodo para imprimir la información de la cita
    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", motivo='" + motivo + '\'' +
                ", doctor='" + doctor + '\'' +
                ", paciente='" + paciente + '\'' +
                '}';
    }
}

