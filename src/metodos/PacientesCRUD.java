//Fernanda Morales
//Clase de métodos CRUD para la subclase Paciente

package metodos;

import entidades.Paciente;

import java.io.*;
import java.util.ArrayList;

public class PacientesCRUD implements Serializable {
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private String rutaArchivo; // Ruta para el archivo

    // Constructor con ruta personalizada
    public PacientesCRUD(String rutaArchivo) {
        this.listaPacientes = new ArrayList<>();
        this.rutaArchivo = rutaArchivo;
        cargarDatos(); // Cargar datos desde el archivo al iniciar
    }

    // CREATE: Agregar un nuevo paciente
    public boolean agregarPaciente(Paciente paciente) {
        if (getInfoPacientePorID(paciente.getId()) == null) {
            listaPacientes.add(paciente);
            guardarDatos(); // Guardar cambios en el archivo
            return true;
        }
        return false; // El paciente ya existe
    }

    // READ: Buscar un paciente por ID
    public Paciente getInfoPacientePorID(String id) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null; // No encontrado
    }

    // UPDATE: Actualizar información de un paciente existente
    public boolean actualizarPaciente(String id, Paciente nuevosDatos) {
        for (int i = 0; i < listaPacientes.size(); i++) {
            if (listaPacientes.get(i).getId().equals(id)) {
                listaPacientes.set(i, nuevosDatos); // Reemplazar con los nuevos datos
                guardarDatos(); // Guardar cambios en el archivo
                return true;
            }
        }
        return false; // Paciente no encontrado
    }

    // DELETE: Eliminar un paciente por ID
    public boolean eliminarPaciente(String id) {
        boolean eliminado = listaPacientes.removeIf(paciente -> paciente.getId().equals(id));
        if (eliminado) {
            guardarDatos(); // Guardar cambios en el archivo
        }
        return eliminado;
    }

    // Guardar datos en un archivo
    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(listaPacientes); // Escribir la lista en el archivo
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    // Cargar datos desde un archivo
    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            listaPacientes = (ArrayList<Paciente>) ois.readObject(); // Leer los datos
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: Se inicia con una lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    // Metodo para obtener todos los pacientes
    public ArrayList<Paciente> getTodosLosPacientes() {
        return listaPacientes;
    }
}





