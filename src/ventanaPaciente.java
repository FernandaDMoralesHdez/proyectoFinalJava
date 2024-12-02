import entidades.Paciente;
import metodos.PacientesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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


        btnBuscarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIDPaciente.getText();
                Paciente paciente = crudPacientes.getInfoPacientePorID(id);

                if (paciente != null) {
                    txtNombrePaciente.setText(paciente.getNombre());
                    txtApellidoPaciente.setText(paciente.getApellido());
                    txtTelefonoPaciente.setText(paciente.getTelefono());
                    txtEmailPaciente.setText(paciente.getEmail());
                    txtFecNacPaciente.setText(paciente.getFechaNacimiento().toString());
                    JOptionPane.showMessageDialog(miPanel, "Paciente encontrado.");
                } else {
                    JOptionPane.showMessageDialog(miPanel, "Paciente no encontrado.");
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
