import metodos.PacientesCRUD;
import entidades.Paciente;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Define la ruta absoluta del archivo
        String rutaArchivo = "D:\\IntelliJ\\Tareas\\proyectoFinal\\baseDatos\\pacientes.dat";

        // Instancia del CRUD con la ruta del archivo
        PacientesCRUD crud = new PacientesCRUD(rutaArchivo);

        // Crear un nuevo paciente
        Paciente paciente1 = new Paciente("1", "Juan", "Perez", "1234567890", "juan.perez@example.com", LocalDate.of(1990, 5, 15), "Sin historial");
        crud.agregarPaciente(paciente1);

        // Consultar un paciente
        Paciente encontrado = crud.getInfoPacientePorID("1");
        if (encontrado != null) {
            System.out.println("Paciente encontrado: " + encontrado.getNombre());
        } else {
            System.out.println("Paciente no encontrado.");
        }

        // Actualizar un paciente
        Paciente datosActualizados = new Paciente("1", "Juan", "Perez", "0987654321", "juan.perez@nuevoemail.com", LocalDate.of(1990, 5, 15), "Historial actualizado");
        crud.actualizarPaciente("1", datosActualizados);

        // Eliminar un paciente
        crud.eliminarPaciente("1");

        // Verificar que se eliminó
        Paciente eliminado = crud.getInfoPacientePorID("1");
        if (eliminado == null) {
            System.out.println("Paciente eliminado correctamente.");
        }
    }
}

