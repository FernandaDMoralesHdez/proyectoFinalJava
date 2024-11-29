//Fernanda Morales
//Clase de métodos CRUD para la subclase Paciente

package metodos;

import entidades.Paciente;

import java.io.Serializable;
import java.util.ArrayList;

public class PacientesCRUD implements Serializable {
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();

    //CREATE
    public void agregarPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
        System.out.println("Paciente agregado correctamente.");
    }

    //READ
    public Paciente getInfoPacientePorID(String id) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        System.out.println("Paciente no encontrado.");
        return null;
    }

    //UPDATE
    public void actualizarPaciente(String id, Paciente nuevosDatos) {
        for (int i = 0; i < listaPacientes.size(); i++) {
            if (listaPacientes.get(i).getId().equals(id)) {
                listaPacientes.set(i, nuevosDatos);
                System.out.println("Paciente actualizado correctamente.");
                return;
            }
        }
        System.out.println("Paciente no encontrado.");
    }

    //DELETE
    public void eliminarPaciente(String id) {
        listaPacientes.removeIf(paciente -> paciente.getId().equals(id));
        System.out.println("Paciente eliminado correctamente.");
    }
}

