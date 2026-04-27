package views.home;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import views.components.Card;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class QueuePanel extends JPanel {
    private JPanel listPanel;

    public QueuePanel(){


        // List Panel
        listPanel = new JPanel();
        listPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        10,
                        10,
                        10
                )
        );

        listPanel.setBackground(Color.decode("#f8f7ff"));
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );



        setLayout(new GridBagLayout());
        setBackground(Color.decode("#ffffff"));
        putClientProperty(FlatClientProperties.STYLE, "arc:16");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(35, 5, 15, 5);


        // HEADER
        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.insets = new Insets(0, 25, 0, 0);

        titlePanel.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        titlePanel.setBackground(Color.decode("#eef0f2"));
        titlePanel.setPreferredSize(new Dimension(1290, 70));

        JLabel titleLabel = new JLabel("Fila de Atendimento");
        titleLabel.setForeground(Color.decode("#0d1b2a"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;

        gbcHeader.anchor = GridBagConstraints.WEST;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;
        gbcHeader.weightx = 1;

        titlePanel.add(titleLabel, gbcHeader);

        add(titlePanel, gbc);


        // Eventos
        JPanel eventPanel = new JPanel(new GridBagLayout());
        eventPanel.setBackground(Color.decode("#ffffff"));
        GridBagConstraints gbcEvents = new GridBagConstraints();
        gbcEvents.insets = new Insets(5,5,5,5);




        eventPanel.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        eventPanel.setPreferredSize(new Dimension(1290, 120));
        //eventPanel.setBackground(Color.BLUE);
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode("#0077b6")),
                "Ações"
        );

        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitleColor(Color.decode("#0077b6"));
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));

        eventPanel.setBorder(border);

        // Buttons
        FlatSVGIcon addIcon = new FlatSVGIcon("views/assets/receptionist/add.svg", 30, 30);
        addIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon updateIcon = new FlatSVGIcon("views/assets/receptionist/update.svg", 35, 35);
        updateIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon binIcon = new FlatSVGIcon("views/assets/receptionist/bin.svg", 30, 30);
        binIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon enqueueIcon = new FlatSVGIcon("views/assets/receptionist/enqueue.svg", 30, 30);
        enqueueIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));



        JButton registerBtn = new JButton("Registrar Paciente", addIcon);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(Color.decode("#0077b6"));
        registerBtn.setFont(new Font("Arial", Font.BOLD, 16));
        registerBtn.setPreferredSize(new Dimension(220, 40));
        gbcEvents.gridx = 0;
        gbcEvents.gridy = 0;
        eventPanel.add(registerBtn, gbcEvents);


        JButton updateBtn = new JButton("Atualizar", updateIcon);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setBackground(Color.decode("#f56416"));
        updateBtn.setFont(new Font("Arial", Font.BOLD, 16));
        updateBtn.setPreferredSize(new Dimension(200, 40));
        gbcEvents.gridx = 1;
        gbcEvents.gridy = 0;
        eventPanel.add(updateBtn, gbcEvents);

        JButton removeBtn = new JButton("Remover", binIcon);
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setBackground(Color.decode("#ea2b1f"));
        removeBtn.setFont(new Font("Arial", Font.BOLD, 16));
        removeBtn.setPreferredSize(new Dimension(200, 40));
        gbcEvents.gridx = 2;
        gbcEvents.gridy = 0;
        eventPanel.add(removeBtn, gbcEvents);

        JButton addToQueueBtn = new JButton("Adicionar à Fila", enqueueIcon);
        addToQueueBtn.setForeground(Color.WHITE);
        addToQueueBtn.setBackground(Color.decode("#069e2d"));
        addToQueueBtn.setPreferredSize(new Dimension(200, 40));
        addToQueueBtn.setFont(new Font("Arial", Font.BOLD, 16));

        gbcEvents.gridx = 3;
        gbcEvents.gridy = 0;
        eventPanel.add(addToQueueBtn, gbcEvents);

        JTextField searchField = new JTextField();
        searchField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        searchField.putClientProperty("JTextField.placeholderText", "Pesquisar Paciente");
        searchField.setPreferredSize(new Dimension(220, 40));
        gbcEvents.gridx = 4;
        gbcEvents.gridy = 0;
        eventPanel.add(searchField, gbcEvents);

        JComboBox<String> combo = new JComboBox<>();
        combo.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        combo.setPreferredSize(new Dimension(150, 40));
        combo.addItem("Filtro");
        combo.addItem("ID");
        combo.addItem("Nome");

        gbcEvents.gridx = 5;
        gbcEvents.gridy = 0;
        eventPanel.add(combo, gbcEvents);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(eventPanel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;

        gbc.weightx = 1;
        gbc.weighty = 1;

        //gbc.fill = GridBagConstraints.BOTH;

        scroll.setPreferredSize(new Dimension(1290, 550));

        add(scroll, gbc);





    }

    public void addPatient(int id, int position,String name, String priority){

        Card card = new Card(id, position, name, priority);

        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        listPanel.add(card);

        listPanel.add(Box.createVerticalStrut(10));

        listPanel.revalidate();
        listPanel.repaint();
    }

    public void removePatient(int id) {
        Component[] components = listPanel.getComponents();

        for (Component c : components) {

            if (c instanceof Card card) {

                if (card.getId() == id) {
                    listPanel.remove(card);
                    break;
                }
            }
        }

        listPanel.revalidate();
        listPanel.repaint();
    }
}
