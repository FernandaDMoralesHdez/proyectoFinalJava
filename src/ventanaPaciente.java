import metodos.PacientesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventanaPaciente extends JFrame{
    private JPanel miPanel;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JTextField txtFecNac;
    private JButton btnBuscar;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;

    public ventanaPaciente() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacientesCRUD crud = new PacientesCRUD(); //Instanciar
                /*String id = txt
                crud.getInfoPacientePorID(id); //Invocar metodo para buscar paciente */

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
