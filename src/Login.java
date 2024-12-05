import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel miPanel;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public Login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Harcodear
                String password = String.valueOf(txtPassword.getPassword());
                String[] tipoUsuario = null;

                //Bienvenida al sistema a Admin para dar de alta pacientes
                if (txtUsuario.getText().equals("Doctor") && password.equals("doc123")) {
                    JOptionPane.showMessageDialog(miPanel, "Bienvenido(a) al sistema para dar de alta pacientes");
                    dispose();
                    tipoUsuario = new String[]{"Doctor"};
                    ventanaPaciente.main(tipoUsuario);

                    //Bienvenida al sistema a Paciente para agendar su cita
                } else if (txtUsuario.getText().equals("Paciente") && password.equals("pac123")) {
                    dispose();
                    JOptionPane.showMessageDialog(miPanel, "Bienvenido(a) al sistema para agendar citas médicas");
                    ventanaCitas.main(tipoUsuario);

                } else {
                    //Contraseño o usuario no válidos
                    JOptionPane.showMessageDialog(miPanel, "El usuario o la contraseña son incorrectos.", "Login", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }

    public static void main(String[] args) {
        Login vLogin = new Login();
        vLogin.setContentPane(vLogin.miPanel);
        vLogin.setSize(300,300);
        vLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        vLogin.setVisible(true);
    }
}
