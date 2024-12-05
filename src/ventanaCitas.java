import metodos.CitasCR;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import static metodos.validacionesCita.validarFormatoFecha;
import static metodos.validacionesCita.validarHora;

public class ventanaCitas extends JFrame {
    private JTextField txtIDCita;
    private JTextField txtFechaCita;
    private JTextField txtMotivoCita;
    private JTextField txtDoctorCita;
    private JTextField txtPacienteCita;
    private JButton btnAgregar;
    private JButton btnBuscar;
    private JPanel miPanel;
    private JTextField txtHora;

    // Instancia de CitasCR para manejar las citas
    private CitasCR citasCR = new CitasCR();

    public ventanaCitas() {

        //Botón para AGREGAR una nueva cita
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar ID de la cita
                    int idCita = Integer.parseInt(txtIDCita.getText());
                    if (citasCR.existeCita(idCita)) { // Metodo que verifica si el ID ya existe
                        JOptionPane.showMessageDialog(null, "El ID de la cita ya existe. Por favor, usa uno diferente.");
                        return;
                    }

                    // Validar fecha (formato dd/MM/yyyy)
                    String fechaCita = txtFechaCita.getText();
                    if (!validarFormatoFecha(fechaCita)) {
                        JOptionPane.showMessageDialog(null, "El formato de la fecha debe ser dd/MM/yyyy (por ejemplo, 04/12/2024).");
                        return;
                    }

                    // Validar hora (formato HH:mm y rango de 6:00 a 22:00)
                    String horaCita = txtHora.getText();
                    if (!validarHora(horaCita)) {
                        JOptionPane.showMessageDialog(null, "La hora debe estar en formato HH:mm (por ejemplo, 14:30) y entre las 06:00 y las 22:00.");
                        return;
                    }

                    // Validar motivo
                    String motivoCita = txtMotivoCita.getText().trim();
                    if (motivoCita.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El campo 'Motivo' no puede estar vacío.");
                        return;
                    }

                    // Validar doctor y paciente
                    String doctorCita = txtDoctorCita.getText().trim();
                    if (doctorCita.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El campo 'Doctor(a)' no puede estar vacío.");
                        return;
                    }

                    String pacienteCita = txtPacienteCita.getText().trim();
                    if (pacienteCita.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El campo 'Paciente' no puede estar vacío.");
                        return;
                    }

                    // Crear la cita y limpiar campos después de agregar
                    citasCR.crearCita(idCita, fechaCita, horaCita, motivoCita, doctorCita, pacienteCita);
                    JOptionPane.showMessageDialog(null, "Cita creada exitosamente.");

                    txtIDCita.setText("");
                    txtFechaCita.setText("");
                    txtHora.setText("");
                    txtMotivoCita.setText("");
                    txtDoctorCita.setText("");
                    txtPacienteCita.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar la cita: " + ex.getMessage());
                }
            }
        });


        //Botón para BUSCAR una cita
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar ID de la cita
                    int idCita = Integer.parseInt(txtIDCita.getText());
                    if (!citasCR.existeCita(idCita)) {
                        JOptionPane.showMessageDialog(null, "La cita con ID " + idCita + " no existe.");
                        return;
                    }

                    // Buscar la cita y mostrarla
                    JOptionPane.showMessageDialog(null, "Cita encontrada: " + citasCR.buscarCita(idCita));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar la cita: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        ventanaCitas vCitas = new ventanaCitas();
        vCitas.setContentPane(vCitas.miPanel);
        vCitas.setSize(500,300);
        vCitas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vCitas.setVisible(true);
    }
}
