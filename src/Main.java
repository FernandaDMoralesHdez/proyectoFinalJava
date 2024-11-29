//Fernanda Morales

import entidades.Paciente;
import metodos.PacientesCRUD;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //Instancia del CRUD
        PacientesCRUD pacientesCRUD = new PacientesCRUD();

        //Crear tres pacientes
        Paciente p1 = new Paciente("1", "Fernanda", "Morales", "9911223344", "fer@gmail.com",
        LocalDate.of(1995, 5, 12), 1, "Sin antecedentes médicos.");
Paciente p2 = new Paciente("2", "Luis", "Pérez", "9876543210", "luis@gmail.com",
        LocalDate.of(1990, 8, 25), 2, "Alergia a penicilina.");
Paciente p3 = new Paciente("3", "María", "González", "5555555555", "maria@gmail.com",
        LocalDate.of(2000, 3, 15), 3, "Hipertensión controlada.");
        //Agregar pacientes a la lista
        pacientesCRUD.agregarPaciente(p1);
        pacientesCRUD.agregarPaciente(p2);
        pacientesCRUD.agregarPaciente(p3);

        // Guardar la lista de pacientes en un archivo
        try {
            FileOutputStream escribir = new FileOutputStream("D:\\IntelliJ\\Tareas\\proyectoFinal\\baseDatos\\pacientes.dat");
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(pacientesCRUD);
            miStream.flush();
            miStream.close();
            System.out.println("Lista de pacientes guardada en base de datos.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}
