package metodos;

import entidades.Citas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CitasCRUD {
    // Lista para almacenar las citas
    private List<Citas> citasList = new ArrayList<>();

    // Crear una nueva cita
    public void crearCita(int idCita, LocalDate fecha, LocalTime hora, String motivo, String doctor, String paciente) {
        Citas nuevaCita = new Citas(idCita, fecha, hora, motivo, doctor, paciente);
        citasList.add(nuevaCita);
        System.out.println("Cita creada: " + nuevaCita);
    }

    // Leer todas las citas
    public void mostrarCitas() {
        for (Citas cita : citasList) {
            System.out.println(cita);
        }
    }

    // Actualizar una cita
    public void actualizarCita(int idCita, LocalDate nuevaFecha, LocalTime nuevaHora, String nuevoMotivo) {
        for (Citas cita : citasList) {
            if (cita.getIdCita() == idCita) {
                cita.setFecha(nuevaFecha);
                cita.setHora(nuevaHora);
                cita.setMotivo(nuevoMotivo);
                System.out.println("Cita actualizada: " + cita);
                return;
            }
        }
        System.out.println("Cita no encontrada.");
    }

    // Eliminar una cita
    public void eliminarCita(int idCita) {
        Citas citaAEliminar = null;
        for (Citas cita : citasList) {
            if (cita.getIdCita() == idCita) {
                citaAEliminar = cita;
                break;
            }
        }
        if (citaAEliminar != null) {
            citasList.remove(citaAEliminar);
            System.out.println("Cita eliminada: " + citaAEliminar);
        } else {
            System.out.println("Cita no encontrada.");
        }
    }
}
