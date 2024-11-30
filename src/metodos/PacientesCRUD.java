//Fernanda Morales
//Clase de métodos CRUD para la subclase Paciente

package metodos;

import entidades.Paciente;

import java.io.*;
import java.util.ArrayList;

public class PacientesCRUD implements Serializable {
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();

    //CREATE: Agregar un paciente validando que no haya duplicados
    public boolean agregarPaciente(Paciente paciente) {
        if (getInfoPacientePorID(paciente.getId()) == null) { //Verificar si el ID no existe
            listaPacientes.add(paciente);
            System.out.println("Paciente agregado correctamente.");
            return true; //Éxito de la operación CREATE
        }
        System.out.println("Error: Ya existe un paciente con el mismo ID. Intenta ingresar otro ID.");
        return false; //No se pudo agregar debido a duplicado
    }

    //READ: Obtener información de un paciente por ID
    public Paciente getInfoPacientePorID(String id) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null; //Paciente no encontrado
    }

    //UPDATE: Actualizar datos de un paciente existente
    public boolean actualizarPaciente(String id, Paciente nuevosDatos) {
        for (int i = 0; i < listaPacientes.size(); i++) {
            if (listaPacientes.get(i).getId().equals(id)) {
                listaPacientes.set(i, nuevosDatos); //Reemplaza los datos del paciente
                System.out.println("Paciente actualizado correctamente.");
                return true; //Éxito en la operación UPDATE
            }
        }
        System.out.println("Error: Paciente no encontrado.");
        return false; //No se actualiza debido a que no se encontró el paciente
    }

    //DELETE: Eliminar un paciente por ID
    public boolean eliminarPaciente(String id) {
        boolean eliminado = listaPacientes.removeIf(paciente -> paciente.getId().equals(id));
        if (eliminado) {
            System.out.println("Paciente eliminado correctamente.");
            return true; //Éxito en la operación DELETE
        }
        System.out.println("Error: Paciente no encontrado.");
        return false; //No se elimina debido a que no se encontró el paciente
    }

    //Metodo para obtener todos los pacientes
    public ArrayList<Paciente> getTodosLosPacientes() {
        return listaPacientes;
    }

    //Guardar la lista de pacientes en un archivo (serialización)
    public void guardarDatos(String archivoRuta) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoRuta))) {
            oos.writeObject(listaPacientes);
            System.out.println("Datos guardados correctamente en " + archivoRuta);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    //Cargar la lista de pacientes desde un archivo (deserialización)
    public void cargarDatos(String archivoRuta) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoRuta))) {
            listaPacientes = (ArrayList<Paciente>) ois.readObject();
            System.out.println("Datos cargados correctamente desde " + archivoRuta);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + archivoRuta);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

}


