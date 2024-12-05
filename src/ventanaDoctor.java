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
        //Botón AGREGAR doctor

        btnAgregarDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los datos de los campos de texto
                    String idDoctor = txtIDDoctor.getText();
                    String nombreDoctor = txtNombreDoctor.getText();
                    String apellidoDoctor = txtApellidosDoctor.getText();
                    String telefonoDoctor = txtTelefonoDoctor.getText();
                    String emailDoctor = txtEmailDoctor.getText();
                    LocalDate fechaNacimiento = LocalDate.parse(txtFechaNacDoctor.getText()); // Formato: yyyy-MM-dd
                    String especialidadDoctor = txtEspecialidadDoctor.getText();

                    // Crear el objeto Doctor
                    Doctor nuevoDoctor = new Doctor(idDoctor, nombreDoctor, apellidoDoctor, telefonoDoctor, emailDoctor, fechaNacimiento, especialidadDoctor);

                    // Instanciar el CRUD de Doctores y agregar el doctor
                    DoctoresCRUD doctoresCRUD = new DoctoresCRUD("ruta/del/archivo/doctores.dat");
                    if (doctoresCRUD.agregarDoctor(nuevoDoctor)) {
                        JOptionPane.showMessageDialog(null, "Doctor agregado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "El doctor con ese ID ya existe.");
                    }

                    // Limpiar los campos de texto
                    txtIDDoctor.setText("");
                    txtNombreDoctor.setText("");
                    txtApellidosDoctor.setText("");
                    txtTelefonoDoctor.setText("");
                    txtEmailDoctor.setText("");
                    txtFechaNacDoctor.setText("");
                    txtEspecialidadDoctor.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar el doctor: " + ex.getMessage());
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
