package views.home;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controllers.DoctorController;
import controllers.ReceptionistController;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import views.components.BarChartPanel;
import views.components.CardDash;
import views.components.PieChartPanel;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private CardDash numberOfPatients;
    private CardDash availableDoctors;
    private CardDash nextPatient;
    private CardDash highPriority;

    public DashboardPanel(ReceptionistController rec, DoctorController doc){

        setBackground(Color.decode("#ffffff"));
        setLayout(new GridBagLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:16");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);


        // HEADER
        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.insets = new Insets(0, 25, 0, 0);

        titlePanel.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        titlePanel.setBackground(Color.decode("#e5e5e5"));
        titlePanel.setPreferredSize(new Dimension(1290, 70));

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setForeground(Color.decode("#0d1b2a"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;

        gbcHeader.anchor = GridBagConstraints.WEST;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;
        gbcHeader.weightx = 1;

        titlePanel.add(titleLabel, gbcHeader);

        add(titlePanel, gbc);




        // Cards
        JPanel cardsPanel = new JPanel(new GridBagLayout());
        cardsPanel.setBackground(Color.decode("#ffffff"));
        cardsPanel.setPreferredSize(new Dimension(1290, 250));
        GridBagConstraints gbcCards = new GridBagConstraints();
        gbcCards.insets = new Insets(5, 15,15,5);

        numberOfPatients = new CardDash(rec.getPatientQueue().size() + rec.getPriorityQueue().size(), "Pacientes na Fila", "0582ca");
        cardsPanel.add(numberOfPatients, gbcCards);

        availableDoctors = new CardDash(doc.loadDoctors().size(), "Médicos Disponíveis", "25a244");
        cardsPanel.add(availableDoctors, gbcCards);

        if (rec.getPatientQueue().peek() == null || rec.getPatientQueue().size() == 0 || rec.getPriorityQueue().size() == 0){
            nextPatient = new CardDash("Fila Vazia", "Próximo Paciente", "5a189a");
            cardsPanel.add(nextPatient, gbcCards);
        }else {
            nextPatient = new CardDash(rec.getPatientQueue().peek().getName(), "Próximo Paciente", "5a189a");
            cardsPanel.add(nextPatient, gbcCards);
        }


        highPriority = new CardDash(rec.getPriorityQueue().size(), "Prioridade Alta", "f40000");
        cardsPanel.add(highPriority, gbcCards);


        gbc.gridx = 0;
        gbc.gridy= 1;
        add(cardsPanel, gbc);


        // DownPanel
        JPanel downPanel = new JPanel(new GridBagLayout());
        downPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#0077b6"), 2));
        downPanel.setBackground(Color.decode("#ffffff"));
        downPanel.setPreferredSize(new Dimension(1290, 500));
        GridBagConstraints gbcDown = new GridBagConstraints();
        gbcDown.insets = new Insets(10, 20, 10, 20);

        // Ações rápidas


        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(Color.decode("#edf2fb"));
        buttonsPanel.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        buttonsPanel.setPreferredSize(new Dimension(400, 300));
        GridBagConstraints gbcBtns = new GridBagConstraints();
        gbcBtns.insets = new Insets(10, 10, 10, 10);

        // Buttons
        FlatSVGIcon addIcon = new FlatSVGIcon("views/assets/receptionist/add.svg", 30, 30);
        addIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon seeIcon = new FlatSVGIcon("views/assets/dashboard/see.svg", 30, 30);
        seeIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon updateIcon = new FlatSVGIcon("views/assets/receptionist/update.svg", 35, 35);
        updateIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));


        JButton addPatientBtn = new JButton("Registrar Paciente", addIcon);
        addPatientBtn.setBackground(Color.decode("#0077b6"));
        addPatientBtn.setForeground(Color.WHITE);
        addPatientBtn.setPreferredSize(new Dimension(380, 50));
        addPatientBtn.setFont(new Font("Arial", Font.BOLD, 16));

        gbcBtns.gridx = 0;
        gbc.gridy = 0;
        buttonsPanel.add(addPatientBtn, gbcBtns);

        JButton seeQueueBtn = new JButton("Ver Fila", seeIcon);
        seeQueueBtn.setBackground(Color.decode("#7b2cbf"));
        seeQueueBtn.setForeground(Color.WHITE);
        seeQueueBtn.setPreferredSize(new Dimension(380, 50));
        seeQueueBtn.setFont(new Font("Arial", Font.BOLD, 16));

        gbcBtns.gridx = 0;
        gbc.gridy = 1;
        buttonsPanel.add(seeQueueBtn, gbcBtns);

        JButton updateDataBtn = new JButton("Atualizar Dados", updateIcon);
        updateDataBtn.setForeground(Color.WHITE);
        updateDataBtn.setBackground(Color.decode("#f56416"));
        updateDataBtn.setPreferredSize(new Dimension(380, 50));
        updateDataBtn.setFont(new Font("Arial", Font.BOLD, 16));

        gbcDown.gridx = 0;
        gbcDown.gridy = 0;
        downPanel.add(buttonsPanel);

        gbcBtns.gridx = 0;
        gbc.gridy = 2;
        buttonsPanel.add(updateDataBtn, gbcBtns);

        gbcDown.gridx = 1;
        gbcDown.gridy = 0;
        downPanel.add(new PieChartPanel(), gbcDown);

        gbcBtns.gridx = 2;
        gbcBtns.gridy = 0;
        downPanel.add(new BarChartPanel(), gbcBtns);




        gbc.gridx = 0;
        gbc.gridy = 2;
        add(downPanel, gbc);


    }

    public CardDash getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(CardDash numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public CardDash getAvailableDoctors() {
        return availableDoctors;
    }

    public void setAvailableDoctors(CardDash availableDoctors) {
        this.availableDoctors = availableDoctors;
    }

    public CardDash getNextPatient() {
        return nextPatient;
    }

    public void setNextPatient(CardDash nextPatient) {
        this.nextPatient = nextPatient;
    }

    public CardDash getHighPriority() {
        return highPriority;
    }

    public void setHighPriority(CardDash highPriority) {
        this.highPriority = highPriority;
    }
}
