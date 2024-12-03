import entidades.Paciente;
import metodos.PacientesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ventanaPaciente extends JFrame{
    private JPanel miPanel;
    private JTextField txtIDPaciente;
    private JTextField txtNombrePaciente;
    private JTextField txtApellidoPaciente;
    private JTextField txtTelefonoPaciente;
    private JTextField txtEmailPaciente;
    private JTextField txtFecNacPaciente;
    private JButton btnBuscarPaciente;
    private JButton btnAgregarPaciente;
    private JButton btnEditarPaciente;
    private JButton btnEliminarPaciente;
    private PacientesCRUD crudPacientes; //Instancia global de CRUD


    public ventanaPaciente() {
        crudPacientes = new PacientesCRUD("D:\\IntelliJ\\Tareas\\proyectoFinal\\baseDatos\\pacientes.dat"); //Inicializar el CRUD una sola vez

        //Botón BUSCAR paciente por ID
        btnBuscarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIDPaciente.getText();
                Paciente paciente = crudPacientes.getInfoPacientePorID(id);

                if (paciente != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    txtNombrePaciente.setText(paciente.getNombre());
                    txtApellidoPaciente.setText(paciente.getApellido());
                    txtTelefonoPaciente.setText(paciente.getTelefono());
                    txtEmailPaciente.setText(paciente.getEmail());
                    txtFecNacPaciente.setText(paciente.getFechaNacimiento().format(formatter)); // Formato dd/MM/yyyy
                    JOptionPane.showMessageDialog(miPanel, "Paciente encontrado.");
                } else {
                    JOptionPane.showMessageDialog(miPanel, "Paciente no encontrado.");
                }
            }
        });

        //Botón AGREGAR paciente
        btnAgregarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Validar el ID
                    String id = txtIDPaciente.getText().trim();
                    if (id.isEmpty()) {
                        throw new IllegalArgumentException("El ID no puede estar vacío.");
                    }

                    //Validar el nombre
                    String nombre = txtNombrePaciente.getText().trim();
                    if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                        throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
                    }

                    // Validar el apellido
                    String apellido = txtApellidoPaciente.getText().trim();
                    if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                        throw new IllegalArgumentException("El apellido solo puede contener letras y espacios.");
                    }

                    // Validar el teléfono
                    String telefono = txtTelefonoPaciente.getText().trim();
                    if (!telefono.matches("\\d{10}")) {
                        throw new IllegalArgumentException("El teléfono debe contener exactamente 10 dígitos.");
                    }

                    // Validar el email
                    String email = txtEmailPaciente.getText().trim();
                    if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                        throw new IllegalArgumentException("Por favor, introduce un email válido.");
                    }

                    // Validar la fecha de nacimiento
                    String fechaInput = txtFecNacPaciente.getText().trim();
                    if (!fechaInput.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        throw new IllegalArgumentException("La fecha debe estar en el formato dd/MM/yyyy.");
                    }

                    // Parsear y validar la fecha
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fechaNacimiento;
                    try {
                        fechaNacimiento = LocalDate.parse(fechaInput, formatter);
                    } catch (Exception ex) {
                        throw new IllegalArgumentException("Fecha inválida. Por favor, verifica el día, mes y año.");
                    }

                    // Crear el nuevo paciente
                    Paciente nuevoPaciente = new Paciente(id, nombre, apellido, telefono, email, fechaNacimiento, "Sin historial");

                    // Agregar el paciente al CRUD
                    if (crudPacientes.agregarPaciente(nuevoPaciente)) {
                        JOptionPane.showMessageDialog(miPanel, "Paciente agregado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(miPanel, "El paciente ya existe con este ID.");
                    }

                    // Limpiar los campos después de agregar
                    txtIDPaciente.setText("");
                    txtNombrePaciente.setText("");
                    txtApellidoPaciente.setText("");
                    txtTelefonoPaciente.setText("");
                    txtEmailPaciente.setText("");
                    txtFecNacPaciente.setText("");

                } catch (IllegalArgumentException ex) {
                    // Mostrar mensajes de error al usuario
                    JOptionPane.showMessageDialog(miPanel, "Error: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        ventanaPaciente v = new ventanaPaciente();
        v.setContentPane(v.miPanel);
        v.setSize(500,500);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
