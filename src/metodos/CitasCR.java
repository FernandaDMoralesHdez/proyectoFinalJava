package metodos;

import entidades.Citas;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CitasCR {
    // Lista para almacenar las citas
    private List<Citas> citasList = new ArrayList<>();

    // Crear una nueva cita
    public void crearCita(int idCita, String fecha, String hora, String motivo, String doctor, String paciente) {
        Citas nuevaCita = new Citas(idCita, fecha, hora, motivo, doctor, paciente);
        citasList.add(nuevaCita);
        System.out.println("Cita creada: " + nuevaCita);
    }

    // Devolver la lista de citas
    public List<Citas> mostrarCitasList() {
        return citasList;
    }

    public boolean existeCita(int idCita) {
        for (Citas cita : citasList) { // "citas" es la lista donde almacenas las citas
            if (cita.getIdCita() == idCita) {
                return true;
            }
        }
        return false;
    }

    public String buscarCita(int idCita) {
        // Iterar sobre la lista de citas (suponiendo que tienes una lista llamada 'citasList')
        for (Citas cita : citasList) {
            // Comparar el idCita de la cita con el idCita pasado como parámetro
            if (cita.getIdCita() == idCita) {
                // Si se encuentra la cita, devolver la representación en String de la cita
                return cita.toString();
            }
        }
        // Si no se encuentra la cita, devolver un mensaje indicando que no se encontró
        return "Cita no encontrada con ID " + idCita;
    }
}

