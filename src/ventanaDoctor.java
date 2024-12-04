import entidades.Doctor;
import metodos.DoctoresCRUD;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ventanaDoctor extends JFrame{
    private JTextField txtIDDoctor;
    private JTextField txtNombreDoctor;
    private JTextField txtApellidosDoctor;
    private JTextField txtTelefonoDoctor;
    private JTextField txtEmailDoctor;
    private JTextField txtFechaNacDoctor;
    private JTextField txtEspecialidadDoctor;
    private JButton btnBuscarDoc;
    private JButton btnAgregarDoc;
    private JButton btnEditarDoc;
    private JButton btnEliminarDoc;
    private JPanel miPanelDoctores;
    private DoctoresCRUD crudDoctores; //Instancia global de CRUD


    public ventanaDoctor() {

        crudDoctores = new DoctoresCRUD("D:\\IntelliJ\\Tareas\\proyectoFinal\\baseDatos\\doctores.dat");

        //Botón BUSCAR doctor por ID
        btnBuscarDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIDDoctor.getText();
                Doctor doctor = crudDoctores.getInfoDoctorPorID(id);
                if (doctor != null) {
                    txtNombreDoctor.setText(doctor.getNombre());
                    txtApellidosDoctor.setText(doctor.getApellido());
                    txtTelefonoDoctor.setText(doctor.getTelefono());
                    txtEmailDoctor.setText(doctor.getEmail());
                    txtFechaNacDoctor.setText(doctor.getFechaNacimiento().toString());
                    txtEspecialidadDoctor.setText(doctor.getEspecialidad());
                } else {
                    JOptionPane.showMessageDialog(null, "Doctor no encontrado.");
                }
            }
        });

        //Botón AGREGAR doctor
        btnAgregarDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Validar el ID
                    String id = txtIDDoctor.getText().trim();
                    if (id.isEmpty()) {
                        throw new IllegalArgumentException("El ID no puede estar vacío para editar un doctor.");
                    }

                    //Buscar el doctor existente
                    //Doctor doctorExistente = crudDoctores.getInfoDoctorPorID(id);
                    //if (doctorExistente == null) {
                      //  throw new IllegalArgumentException("No se encontró un doctor con el ID proporcionado.");
                    //}

                    //Validar el nombre
                    String nombre = txtNombreDoctor.getText().trim();
                    if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                        throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
                    }
                    //Validar el apellido
                    String apellido = txtApellidosDoctor.getText().trim();
                    if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                        throw new IllegalArgumentException("El apellido solo puede contener letras y espacios.");
                    }
                    //Validar el teléfono
                    String telefono = txtTelefonoDoctor.getText().trim();
                    if (!telefono.matches("\\d{10}")) {
                        throw new IllegalArgumentException("El teléfono debe contener exactamente 10 dígitos.");
                    }
                    //Validar el email
                    String email = txtEmailDoctor.getText().trim();
                    if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                        throw new IllegalArgumentException("Por favor, introduce un email válido.");
                    }
                    //Validar la fecha de nacimiento
                    String fechaInput = txtFechaNacDoctor.getText().trim();
                    if (!fechaInput.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        throw new IllegalArgumentException("La fecha debe estar en el formato dd/MM/yyyy.");
                    }
                    //Parsear y validar la fecha
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fechaNacimiento;
                    try {
                        fechaNacimiento = LocalDate.parse(fechaInput, formatter);
                    } catch (Exception ex) {
                        throw new IllegalArgumentException("Fecha inválida. Por favor, verifica el día, mes y año.");
                    }

                    //Crear el nuevo objeto doctor con los datos actualizados
                    Doctor doctorActualizado = new Doctor(id, nombre, apellido, telefono, email, fechaNacimiento, txtEspecialidadDoctor.getText());

                    //Actualizar el doctor en el CRUD
                    if (crudDoctores.actualizarDoctor(id, doctorActualizado)) {
                        JOptionPane.showMessageDialog(miPanelDoctores, "Doctor editado correctamente.");
                        //limpiarCampos(); //Llamada del metodo para limpiar los campos después de editar
                    } else {
                        JOptionPane.showMessageDialog(miPanelDoctores, "No se pudo editar el doctor.");
                    }

                } catch (IllegalArgumentException ex) {
                    //Mostrar mensajes de error al usuario
                    JOptionPane.showMessageDialog(miPanelDoctores, "Error: " + ex.getMessage() + "Inténtalo de nuevo.");
                }
            }
        });
    }

    public static void main(String[] args) {
        ventanaDoctor v = new ventanaDoctor();
        v.setContentPane(v.miPanelDoctores);
        v.setSize(500,500);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
