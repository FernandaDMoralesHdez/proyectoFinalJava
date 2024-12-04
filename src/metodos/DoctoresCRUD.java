package metodos;

import entidades.Doctor;

import java.io.*;
import java.util.ArrayList;

public class DoctoresCRUD implements Serializable {
    private ArrayList<Doctor> listaDoctores = new ArrayList<>();
    private String rutaArchivo; // Ruta para el archivo

    // Constructor con ruta personalizada
    public DoctoresCRUD(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        cargarDatos(); // Cargar datos desde el archivo al iniciar
    }

    // CREATE: Agregar un nuevo doctor
    public boolean agregarDoctor(Doctor doctor) {
        if (getInfoDoctorPorID(doctor.getId()) == null) { // Usar getId() de Persona
            listaDoctores.add(doctor);
            guardarDatos(); // Guardar cambios en el archivo
            return true;
        }
        return false; // El doctor ya existe
    }

    // READ: Buscar un doctor por ID
    public Doctor getInfoDoctorPorID(String id) {
        for (Doctor doctor : listaDoctores) {
            if (doctor.getId().equals(id)) { // Usar getId() de Persona
                return doctor;
            }
        }
        return null; // No encontrado
    }

    // UPDATE: Actualizar información de un doctor existente
    public boolean actualizarDoctor(String id, Doctor nuevosDatos) {
        for (int i = 0; i < listaDoctores.size(); i++) {
            if (listaDoctores.get(i).getId().equals(id)) { // Usar getId() de Persona
                listaDoctores.set(i, nuevosDatos); // Reemplazar con los nuevos datos
                guardarDatos(); // Guardar cambios en el archivo
                return true;
            }
        }
        return false; // Doctor no encontrado
    }

    // DELETE: Eliminar un doctor por ID
    public boolean eliminarDoctor(String id) {
        boolean eliminado = listaDoctores.removeIf(doctor -> doctor.getId().equals(id)); // Usar getId() de Persona
        if (eliminado) {
            guardarDatos(); // Guardar cambios en el archivo
        }
        return eliminado;
    }

    // Guardar datos en un archivo
    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(listaDoctores); // Escribir la lista en el archivo
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    // Cargar datos desde un archivo
    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            listaDoctores = (ArrayList<Doctor>) ois.readObject(); // Leer los datos
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: Se inicia con una lista vacía.");
        } catch (EOFException e) {
            // Esto es esperado si el archivo está vacío.
            System.out.println("Archivo vacío, se inicia con una lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    // Metodo para obtener todos los doctores
    public ArrayList<Doctor> getTodosLosDoctores() {
        return listaDoctores;
    }
}


