import entidades.Paciente;
import metodos.PacientesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class menuPrincipal {
    private JTabbedPane tabbedPane1;
    private JPanel panelMenuPrin;
    private JPanel tabPacientes;
    private JPanel tabDoctores;
    private JPanel tabCitas;
    private JTextField txtNombresPaciente;
    private JTextField txtApellidosPaciente;
    private JTextField txtTelefonoPaciente;
    private JTextField txtEmailPaciente;
    private JTextField txtFechaNacPaciente;
    private JTextField txtIDPaciente;
    private JButton btnBuscarPaciente;
    private JButton btnAgregarPaciente;
    private JButton btnEditarPaciente;
    private JButton btnEliminarPaciente;

    public menuPrincipal() {
        // Acción para agregar paciente
        btnAgregarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que los campos estén completos
                if (txtIDPaciente.getText().isEmpty() || txtNombresPaciente.getText().isEmpty() || txtApellidosPaciente.getText().isEmpty() ||
                        txtTelefonoPaciente.getText().isEmpty() || txtEmailPaciente.getText().isEmpty() || txtFechaNacPaciente.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(panelMenuPrin, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Validamos que el ID sea un número
                    int id = Integer.parseInt(txtIDPaciente.getText());
                    Paciente nuevoPaciente = new Paciente();
                    nuevoPaciente.setId(String.valueOf(id));
                    nuevoPaciente.setNombre(txtNombresPaciente.getText());
                    nuevoPaciente.setApellido(txtApellidosPaciente.getText());
                    nuevoPaciente.setTelefono(txtTelefonoPaciente.getText());
                    nuevoPaciente.setEmail(txtEmailPaciente.getText());
                    nuevoPaciente.setFechaNacimiento(LocalDate.parse(txtFechaNacPaciente.getText())); // Asegúrate que el formato sea correcto

                    // Instanciamos el CRUD y agregamos el paciente
                    PacientesCRUD crud = new PacientesCRUD();
                    if (crud.agregarPaciente(nuevoPaciente)) {
                        JOptionPane.showMessageDialog(panelMenuPrin, "Paciente agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        limpiarFormulario();
                    } else {
                        JOptionPane.showMessageDialog(panelMenuPrin, "Ya existe un paciente con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panelMenuPrin, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelMenuPrin, "Error al agregar el paciente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para buscar paciente
        btnBuscarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validamos que el ID del paciente no esté vacío
                String idPaciente = txtIDPaciente.getText();
                if (idPaciente.isEmpty()) {
                    JOptionPane.showMessageDialog(panelMenuPrin, "Por favor, ingresa el ID del paciente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Buscar paciente por ID
                PacientesCRUD crud = new PacientesCRUD();
                Paciente paciente = crud.getInfoPacientePorID(idPaciente);
                if (paciente != null) {
                    // Mostrar los datos del paciente en los campos
                    txtNombresPaciente.setText(paciente.getNombre());
                    txtApellidosPaciente.setText(paciente.getApellido());
                    txtTelefonoPaciente.setText(paciente.getTelefono());
                    txtEmailPaciente.setText(paciente.getEmail());
                    txtFechaNacPaciente.setText(paciente.getFechaNacimiento().toString());
                } else {
                    JOptionPane.showMessageDialog(panelMenuPrin, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para editar paciente
        btnEditarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que los campos estén completos
                if (txtIDPaciente.getText().isEmpty() || txtNombresPaciente.getText().isEmpty() || txtApellidosPaciente.getText().isEmpty() ||
                        txtTelefonoPaciente.getText().isEmpty() || txtEmailPaciente.getText().isEmpty() || txtFechaNacPaciente.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(panelMenuPrin, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Actualizar la información del paciente
                    Paciente pacienteEditado = new Paciente();
                    pacienteEditado.setId(txtIDPaciente.getText());
                    pacienteEditado.setNombre(txtNombresPaciente.getText());
                    pacienteEditado.setApellido(txtApellidosPaciente.getText());
                    pacienteEditado.setTelefono(txtTelefonoPaciente.getText());
                    pacienteEditado.setEmail(txtEmailPaciente.getText());
                    pacienteEditado.setFechaNacimiento(LocalDate.parse(txtFechaNacPaciente.getText()));

                    // Editar paciente en el CRUD
                    PacientesCRUD crud = new PacientesCRUD();
                    if (crud.actualizarPaciente(String.valueOf(txtIDPaciente), pacienteEditado)) {
                        JOptionPane.showMessageDialog(panelMenuPrin, "Paciente editado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(panelMenuPrin, "Error al editar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelMenuPrin, "Error al editar el paciente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para eliminar paciente
        btnEliminarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que el ID esté presente
                String idPaciente = txtIDPaciente.getText();
                if (idPaciente.isEmpty()) {
                    JOptionPane.showMessageDialog(panelMenuPrin, "Por favor, ingresa el ID del paciente a eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Eliminar paciente
                PacientesCRUD crud = new PacientesCRUD();
                if (crud.eliminarPaciente(idPaciente)) {
                    JOptionPane.showMessageDialog(panelMenuPrin, "Paciente eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(panelMenuPrin, "No se pudo eliminar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void limpiarFormulario() {
        // Limpiar los campos del formulario
        txtIDPaciente.setText("");
        txtNombresPaciente.setText("");
        txtApellidosPaciente.setText("");
        txtTelefonoPaciente.setText("");
        txtEmailPaciente.setText("");
        txtFechaNacPaciente.setText("");
    }

    public static void main(String[] args) {
        // Mostrar la ventana principal
        JFrame frame = new JFrame("Menu Principal");
        menuPrincipal menu = new menuPrincipal();
        frame.setContentPane(menu.panelMenuPrin);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


