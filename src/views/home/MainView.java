package views.home;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controllers.DoctorController;
import controllers.ReceptionistController;
import views.doctor.DoctorPanel;
import views.recptionist.PatientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
    private JFrame root;
    private JPanel contentPanel;
    private ReceptionistController rec;
    private DoctorController doc;
    private CardLayout cardLayout;
    private QueuePanel queuePanel;
    private DashboardPanel dashboardPanel;
    private DoctorPanel  doctorPanel;

    public MainView() {
        root = new JFrame("HCM");
        root.setResizable(false);
        root.setSize(1580, 950);
        root.setLayout(new BorderLayout());
        root.setBackground(Color.decode("#ffffff"));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - root.getWidth()) / 2;
        int y = (screenSize.height - root.getHeight()) / 2;
        root.setLocation(x, y);

        rec = new ReceptionistController();
        doc = new DoctorController();

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        queuePanel = new QueuePanel();
        dashboardPanel = new DashboardPanel(rec, doc);
        doctorPanel = new DoctorPanel(doc, rec, queuePanel,dashboardPanel);

        contentPanel.add(dashboardPanel, "Dashboard");
        contentPanel.add(queuePanel, "Fila");
        contentPanel.add(new PatientPanel(rec, queuePanel,dashboardPanel), "Pacientes");
        contentPanel.add(doctorPanel, "Médicos");
        contentPanel.add(new ReportPanel(), "Relatórios");
        root.add(contentPanel, BorderLayout.CENTER);




        //call components
        initSidebar();
        //initTopbar();


        root.setVisible(true);
    }


    public void initTopbar(){
        JPanel topbar = new JPanel(new BorderLayout());
        topbar.setBackground(Color.decode("#dee2e6"));
        topbar.setPreferredSize(new Dimension(1000, 30));

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.decode("#09509c"));
        logoPanel.setPreferredSize(new Dimension(260, 80));
        topbar.add(logoPanel, BorderLayout.WEST);

        root.add(topbar, BorderLayout.NORTH);

    }


    public void initSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridBagLayout());
        sidebar.setPreferredSize(new Dimension(260, Integer.MAX_VALUE));
        sidebar.setBackground(Color.decode("#09509c"));

        GridBagConstraints gbcSidebar = new GridBagConstraints();
        gbcSidebar.fill = GridBagConstraints.HORIZONTAL;

        // Icons com cor branca
        FlatSVGIcon dashboardIcon = new FlatSVGIcon("views/assets/sidebar/dashboard.svg", 30, 30);
        dashboardIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon patientsIcon = new FlatSVGIcon("views/assets/sidebar/patient.svg", 30, 30);
        patientsIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon doctorsIcon = new FlatSVGIcon("views/assets/sidebar/doctor.svg", 30, 35);
        doctorsIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon queueIcon = new FlatSVGIcon("views/assets/sidebar/queue.svg", 45, 40);
        queueIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon reportIcon = new FlatSVGIcon("views/assets/sidebar/report.svg", 30, 30);
        reportIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon exitIcon = new FlatSVGIcon("views/assets/sidebar/exit.svg", 30, 30);
        exitIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        // Buttons
        JButton dashboardBtn = new JButton("Dashboard", dashboardIcon);
        JButton patientsBtn = new JButton("Pacientes", patientsIcon);
        JButton doctorsBtn = new JButton("Médicos", doctorsIcon);
        JButton queueBtn = new JButton("Fila", queueIcon);
        JButton reportBtn = new JButton("Relatórios", reportIcon);
        JButton exitBtn = new JButton("Sair", exitIcon);

        exitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        exitBtn.setPreferredSize(new Dimension(220, 45));
        exitBtn.setBackground(Color.decode("#09509c"));
        exitBtn.setForeground(Color.WHITE);

        // Buttons list
        JButton[] buttons = {
                dashboardBtn,
                patientsBtn,
                doctorsBtn,
                queueBtn,
                reportBtn
        };

        for (int i = 0; i < buttons.length; i++) {
            JButton btn = buttons[i];

            btn.setForeground(Color.WHITE);
            btn.setBackground(Color.decode("#09509c"));
            btn.setPreferredSize(new Dimension(220, 45));
            btn.setFont(new Font("Arial", Font.BOLD, 16));

            gbcSidebar.insets = new Insets(8, 10, 8, 10);
            gbcSidebar.gridx = 0;
            gbcSidebar.gridy = i;
            gbcSidebar.fill = GridBagConstraints.HORIZONTAL;
            gbcSidebar.weightx = 1.0;

            sidebar.add(btn, gbcSidebar);
        }


        // Suggestions panel
        JPanel suggestionPanel = new JPanel();
        suggestionPanel.setPreferredSize(new Dimension(220, 220));
        suggestionPanel.putClientProperty(
                FlatClientProperties.STYLE,
                "arc:16"
        );

        JLabel suggestionLabel = new JLabel();
        suggestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        suggestionLabel.setVerticalAlignment(SwingConstants.CENTER);
        suggestionPanel.add(suggestionLabel);

        String[] banners = {
                "src/views/assets/sidebar/banner_mascara.png",
                "src/views/assets/sidebar/banner_lavar_maos.png",
                "src/views/assets/sidebar/banner_desinfectar.png"
        };

        final int[] index = {0};

        Runnable trocarImagem = () -> {
            ImageIcon icon = new ImageIcon(banners[index[0]]);
            Image img = icon.getImage().getScaledInstance(230, 190, Image.SCALE_SMOOTH);
            suggestionLabel.setIcon(new ImageIcon(img));
            index[0] = (index[0] + 1) % banners.length;
        };

        trocarImagem.run();

        Timer timer = new Timer(30000, e -> trocarImagem.run());
        timer.start();

        for (int i = 8; i < 13; i++) {
            JPanel p = new JPanel();
            p.setBackground(Color.decode("#09509c"));

            gbcSidebar.gridx = 0;
            gbcSidebar.gridy = i;

            sidebar.add(p, gbcSidebar);
        }

        gbcSidebar.gridx = 0;
        gbcSidebar.gridy = 14;
        sidebar.add(suggestionPanel, gbcSidebar);

        gbcSidebar.gridx = 0;
        gbcSidebar.gridy = 26;
        sidebar.add(exitBtn, gbcSidebar);

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                root.dispose();
            }
        });

        patientsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Pacientes");
            }
        });

        doctorsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel,"Médicos");
            }
        });

        queueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Fila");
            }
        });

        dashboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Dashboard");
            }
        });

        reportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Relatórios");
            }
        });

        root.add(sidebar, BorderLayout.WEST);
    }










    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        new MainView();
    }
}