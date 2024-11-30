import entidades.Paciente;
import metodos.PacientesCRUD;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Instancia del CRUD
        PacientesCRUD pacientesCRUD = new PacientesCRUD();

        // Definir la ruta del archivo
        String rutaArchivo = "D:\\IntelliJ\\Tareas\\proyectoFinal\\baseDatos\\pacientes.dat";

        // Cargar los datos desde el archivo antes de agregar nuevos pacientes
        pacientesCRUD.cargarDatos(rutaArchivo); // Carga los pacientes ya existentes en el archivo

        // Crear tres pacientes nuevos
        Paciente p1 = new Paciente("4", "Fernanda", "Hernández", "9911223355", "fer.hdez@gmail.com",
                LocalDate.of(1995, 5, 12), "Sin antecedentes médicos.");
        Paciente p2 = new Paciente("5", "Luz", "Pérez", "9876543211", "luz@gmail.com",
                LocalDate.of(1990, 8, 25), "Alergia a mariscos.");

        // Agregar pacientes a la lista cargada
        pacientesCRUD.agregarPaciente(p1);
        pacientesCRUD.agregarPaciente(p2);

        // Guardar la lista de pacientes en el archivo (ahora con los nuevos pacientes y los previos)
        pacientesCRUD.guardarDatos(rutaArchivo);

        // Crear un nuevo CRUD para simular una nueva ejecución (cargar los datos y mostrar)
        PacientesCRUD nuevoPacientesCRUD = new PacientesCRUD();

        // Cargar los datos desde el archivo
        nuevoPacientesCRUD.cargarDatos(rutaArchivo);

        // Mostrar todos los pacientes cargados
        System.out.println("\nLista de todos los pacientes:");
        for (Paciente paciente : nuevoPacientesCRUD.getTodosLosPacientes()) {
            System.out.println("ID: " + paciente.getId() + " | Nombre: " + paciente.getNombre() + " " + paciente.getApellido() +
                    " | Teléfono: " + paciente.getTelefono() + " | Email: " + paciente.getEmail() +
                    " | Fecha de Nacimiento: " + paciente.getFechaNacimiento() + " | Historial Médico: " + paciente.getHistorialMedico());
        }
    }
}
