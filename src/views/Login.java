package views;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import models.entities.Doctor;
import services.auth.AuthService;
import services.auth.LoginRequestData;

import javax.swing.*;
import java.awt.*;
import com.fazecast.jSerialComm.SerialPort;
import views.home.MainView;

public class Login {

    private JFrame window;

    private JTextField idField;
    private JPasswordField passField;

    private JLabel statusLabel;

    private AuthService authService;

    // RFID
    private static final String ALLOWED_UID = "D0B7DA6";
    private volatile boolean running = true;

    public Login() {

        authService = new AuthService();

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        window = new JFrame("Login");
        window.setSize(320, 320);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        window.add(title, gbc);

        gbc.gridwidth = 1;


        gbc.gridx = 0;
        gbc.gridy = 1;
        window.add(new JLabel("ID:"), gbc);

        idField = new JTextField();
        idField.putClientProperty(FlatClientProperties.STYLE,"arc:16");
        idField.setPreferredSize(new Dimension(200, 35));

        gbc.gridx = 1;
        window.add(idField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        window.add(new JLabel("Senha:"), gbc);

        passField = new JPasswordField();
        passField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        passField.setPreferredSize(new Dimension(200, 35));

        gbc.gridx = 1;
        window.add(passField, gbc);


        JButton loginBtn = new JButton("Entrar");
        loginBtn.setPreferredSize(new Dimension(200, 40));
        loginBtn.setBackground(Color.decode("#0466c8"));
        loginBtn.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        window.add(loginBtn, gbc);


        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 15));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        window.add(statusLabel, gbc);

        loginBtn.addActionListener(e -> loginManual());

        window.setVisible(true);


        startRFIDListener();
    }


    private void loginManual() {

        String id = idField.getText();
        String pass = new String(passField.getPassword());

        if (id.isEmpty() || pass.isEmpty()) {
            showStatus("Preencha todos os campos", Color.RED);
            return;
        }

        try {
            LoginRequestData data = new LoginRequestData();
            data.setId(Integer.parseInt(id));
            data.setPassword(pass);

            Doctor d = authService.loginDoctor(data);

            if (d != null) {
                showStatus("Login com sucesso", new Color(0, 150, 0));
                openMain();
            } else {
                showStatus("Login inválido", Color.RED);
            }

        } catch (Exception ex) {
            showStatus("Erro no login", Color.RED);
        }
    }


    private void startRFIDListener() {

        new Thread(() -> {

            SerialPort port = SerialPort.getCommPort("/dev/ttyACM0");
            port.setBaudRate(9600);

            if (!port.openPort()) {
                System.out.println("Erro ao abrir porta RFID");
                return;
            }

            byte[] buffer = new byte[1024];

            while (running) {

                int read = port.readBytes(buffer, buffer.length);

                if (read > 0) {

                    String uid = new String(buffer, 0, read);


                    uid = uid.replaceAll("[^A-Za-z0-9]", "");

                    System.out.println("UID: " + uid);

                    if (uid.contains(ALLOWED_UID)) {

                        running = false;

                        SwingUtilities.invokeLater(() -> {
                            showStatus("Login com sucesso (RFID)", new Color(0, 150, 0));
                            openMain();
                        });

                        break;

                    } else {
                        System.out.println("Cartão inválido");
                    }
                }

                try {
                    Thread.sleep(200);
                } catch (Exception ignored) {}
            }

            port.closePort();

        }).start();
    }


    private void showStatus(String msg, Color color) {

        statusLabel.setText(msg);
        statusLabel.setForeground(color);

        new javax.swing.Timer(1500, e -> statusLabel.setText("")) {{
            setRepeats(false);
            start();
        }};
    }


    private void openMain() {

        window.dispose();

        new MainView();
    }

    public static void main(String[] args) {
        new Login();
    }
}