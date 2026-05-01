package views.doctor;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controllers.DoctorController;
import controllers.ReceptionistController;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.DoubleLinkedList.Node;
import models.entities.Doctor;
import models.entities.Patient;
import models.entities.Priority;
import services.doctor.DoctorResgisterRequest;
import views.home.DashboardPanel;
import views.home.QueuePanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorPanel extends JPanel {
    public DoctorPanel(DoctorController doc, ReceptionistController rec, QueuePanel queuePanel, DashboardPanel dash){
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#ffffff"));
        putClientProperty(FlatClientProperties.STYLE, "arc:16");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);

        // Lists
        DoubleLinkedList doctors = doc.loadDoctors();


        // HEADER
        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.insets = new Insets(0, 25, 0, 0);

        titlePanel.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        titlePanel.setBackground(Color.decode("#eef0f2"));
        titlePanel.setPreferredSize(new Dimension(1290, 70));

        JLabel titleLabel = new JLabel("Gestão de Médicos");
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
                "Gestão de Médicos"
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



        JButton registerBtn = new JButton("Registrar Médico", addIcon);
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
        combo.addItem("Especialidade");

        gbcEvents.gridx = 5;
        gbcEvents.gridy = 0;
        eventPanel.add(combo, gbcEvents);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(eventPanel, gbc);

        // Create Table
        DefaultTableModel modelTable = new DefaultTableModel(new Object[]{"ID", "Nome", "Especialidade", "Telefone"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(modelTable);

        JTable table = new JTable(modelTable);
        table.setRowSorter(sorter);

        table.setShowGrid(true);
        table.getTableHeader().setPreferredSize(new Dimension(0, 60));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(Color.decode("#0466c8"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(60);

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            private void filter() {

                String text = searchField.getText().trim();
                String selected = combo.getSelectedItem().toString();

                int column = -1;

                switch (selected) {
                    case "ID":
                        column = 0;
                        break;
                    case "Nome":
                        column = 1;
                        break;
                    default:
                        column = -1; // filtro geral
                }

                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    if (column == -1) {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, column));
                    }
                }
            }

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1290, 350));

        /// Carregar dados
        Node current = doctors.getHead();

        while (current != null){
            Doctor d = (Doctor) current.getElement();

            modelTable.addRow(new Object[]{
                    d.getId(),
                    d.getName(),
                    d.getSpeciality(),
                    d.getPhoneNumber()

            });

            current = current.getNext();
        }



        gbc.gridx = 0;
        gbc.gridy = 2;
        add(scrollPane, gbc);

        // Register doctor
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel registerForm = new JPanel(new GridBagLayout());
                GridBagConstraints gbcForm = new GridBagConstraints();
                gbcForm.insets = new Insets(8, 8, 8, 8);
                gbcForm.fill = GridBagConstraints.HORIZONTAL;
                gbcForm.weightx = 1;

                Dimension fieldSize = new Dimension(300, 40);

                JTextField nameField = new JTextField();
                nameField.putClientProperty(FlatClientProperties.STYLE,"arc:16");
                nameField.putClientProperty("JTextField.placeholderText", "Nome");
                nameField.setPreferredSize(fieldSize);

                JTextField specialityField = new JTextField();
                specialityField.putClientProperty(FlatClientProperties.STYLE,"arc:16");
                specialityField.putClientProperty("JTextField.placeholderText", "Especialidade");
                specialityField.setPreferredSize(fieldSize);

                JTextField phoneField = new JTextField();
                phoneField.putClientProperty(FlatClientProperties.STYLE,"arc:16");
                phoneField.putClientProperty("JTextField.placeholderText", "Especialidade");
                phoneField.setPreferredSize(fieldSize);

                JTextField passField = new JTextField();
                passField.putClientProperty(FlatClientProperties.STYLE,"arc:16");
                passField.putClientProperty("JTextField.placeholderText", "Palavra-Passe");
                passField.setPreferredSize(fieldSize);

                // Name
                gbcForm.gridx = 0;
                gbcForm.gridy = 0;
                registerForm.add(new JLabel("Nome:"), gbcForm);
                gbcForm.gridx = 1;
                registerForm.add(nameField, gbcForm);

                //Speciality
                gbcForm.gridx = 0;
                gbcForm.gridy = 1;
                registerForm.add(new JLabel("Especialidade:"), gbcForm);
                gbcForm.gridx = 1;
                registerForm.add(specialityField, gbcForm);

                // Phone
                gbcForm.gridx = 0;
                gbcForm.gridy = 2;
                registerForm.add(new JLabel("Telefone"), gbcForm);
                gbcForm.gridx = 1;
                registerForm.add(phoneField, gbcForm);


                // Password
                gbcForm.gridx = 0;
                gbcForm.gridy = 3;
                registerForm.add(new JLabel("Palavra-Passe"), gbcForm);
                gbcForm.gridx = 1;
                registerForm.add(passField, gbcForm);

                JButton saveBtn = new JButton("Salvar");
                saveBtn.setBackground(Color.decode("#0466c8"));
                saveBtn.setForeground(Color.WHITE);
                saveBtn.setPreferredSize(new Dimension(300, 35));

                gbcForm.gridx = 0;
                gbcForm.gridy = 4;
                gbcForm.gridwidth = 2;
                registerForm.add(saveBtn, gbcForm);

                JDialog dialog = new JDialog((Frame) null, "Registrar Médico", true);
                dialog.setSize(500, 350);
                dialog.setLocationRelativeTo(null);
                dialog.setContentPane(registerForm);


                saveBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String name = nameField.getText();
                        String speciality = specialityField.getText();
                        String phone = phoneField.getText();
                        String password = passField.getText();


                        if (name.isEmpty() || speciality.isEmpty() || phone.isEmpty() || password.isEmpty()){
                            JOptionPane.showMessageDialog(dialog, "Preencha todos os campos");
                            return;
                        }

                        DoctorResgisterRequest d = new DoctorResgisterRequest();
                        d.setName(name);
                        d.setSpeciality(speciality);
                        d.setPhoneNumber(phone);
                        d.setPassword(password);

                        doc.register(d);




                        modelTable.addRow(new Object[]{
                                d.getId(),
                                name,
                                speciality,
                                phone
                        });

                        JOptionPane.showMessageDialog(dialog, "Médico registado com sucesso!");
                        dialog.dispose();



                    }

                });
                dialog.setVisible(true);



            }

        });


        // Remove Doctor
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();

                if (viewRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um Médico!");
                    return;
                }

                int row = table.convertRowIndexToModel(viewRow);


                String nome = modelTable.getValueAt(row, 1).toString();

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Tens a certeza que quer remover médico: " + nome + "?",
                        "Confirmar Remoção",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION){
                    doc.removeDocter((int)modelTable.getValueAt(row, 0));
                    modelTable.removeRow(row);
                    JOptionPane.showMessageDialog(null, "Médico removido com sucesso!");
                }
            }
        });

        // Update Doctor

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();

                if (viewRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um Médico!");
                    return;
                }

                int row = table.convertRowIndexToModel(viewRow);


                // converter para modelo (importante com sorter)
                row = table.convertRowIndexToModel(row);

                String id = modelTable.getValueAt(row, 0).toString();
                String nome = modelTable.getValueAt(row, 1).toString();
                String speciality = modelTable.getValueAt(row, 2).toString();
                String phone = modelTable.getValueAt(row, 3).toString();

                JTextField nameField = new JTextField(nome);
                nameField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                JTextField specField = new JTextField(speciality);
                specField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                JTextField phoneField = new JTextField(phone);
                phoneField.putClientProperty(FlatClientProperties.STYLE, "arc:16");

                JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
                panel.add(new JLabel("Nome:"));
                panel.add(nameField);
                panel.add(new JLabel("Especialidade:"));
                panel.add(specField);
                panel.add(new JLabel("Telefone:"));
                panel.add(phoneField);

                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "Atualizar Médico",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (result == JOptionPane.OK_OPTION){
                    if (nameField.getText().isEmpty() ||
                            specField.getText().isEmpty() ||
                            phoneField.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                        return;
                    }

                    Doctor d = new Doctor();
                    d.setName(nameField.getText());
                    d.setSpeciality(specField.getText());
                    d.setPhoneNumber(phoneField.getText());
                    d.setId((int)modelTable.getValueAt(row, 0));

                    doc.updateDoctor(d);

                    modelTable.setValueAt(nameField.getText(), row, 1);
                    modelTable.setValueAt(specField.getText(), row, 2);
                    modelTable.setValueAt(phoneField.getText(), row, 3);

                    JOptionPane.showMessageDialog(null, "Médico atualizado com sucesso!");
                }
            }
        });




        // Atendimento
        JPanel appointmentEvents = new JPanel(new GridLayout(1, 2));
        appointmentEvents.setPreferredSize(new Dimension(1290, 250));

        TitledBorder AppoitmentBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode("#0077b6")),
                "Área de Atendimento"
        );

        AppoitmentBorder.setTitleJustification(TitledBorder.LEFT);
        AppoitmentBorder.setTitleFont(new Font("Arial", Font.BOLD, 16));
        AppoitmentBorder.setTitleColor(Color.decode("#0077b6"));
        appointmentEvents.setBorder(AppoitmentBorder);

        // Painel para botões de atendimento
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.insets = new Insets(5, 0, 10, 10);
        gbcButtons.fill = GridBagConstraints.HORIZONTAL;

        // Icones para os botoes
        FlatSVGIcon nextIcon = new FlatSVGIcon("views/assets/doctor/next.svg", 35, 35);
        nextIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.white));

        FlatSVGIcon startIcon = new FlatSVGIcon("views/assets/doctor/start.svg", 33, 33);
        startIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon cancelIcon = new FlatSVGIcon("views/assets/doctor/cancel.svg", 30, 30);
        cancelIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#495057")));


        JButton callBtn = new JButton("Chamar Próximo", nextIcon);
        callBtn.setForeground(Color.WHITE);
        callBtn.setBackground(Color.decode("#0077b6"));
        callBtn.setFont(new Font("Arial", Font.BOLD, 16));
        callBtn.setPreferredSize(new Dimension(500, 50));
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 0;

        buttonsPanel.add(callBtn, gbcButtons);

        JButton attendBtn = new JButton("Iniciar Atendimento", startIcon);
        attendBtn.setForeground(Color.WHITE);
        attendBtn.setBackground(Color.decode("#069e2d"));
        attendBtn.setFont(new Font("Arial", Font.BOLD, 16));
        attendBtn.setPreferredSize(new Dimension(600, 50));
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 1;

        buttonsPanel.add(attendBtn, gbcButtons);


        JButton cancelBtn = new JButton("Cancelar Atendimento", cancelIcon);
        cancelBtn.setForeground(Color.decode("#495057"));
        cancelBtn.setBackground(Color.decode("#adb5bd"));
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 16));
        cancelBtn.setPreferredSize(new Dimension(500, 50));
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 2;

        buttonsPanel.add(cancelBtn, gbcButtons);
        appointmentEvents.add(buttonsPanel);

        // Painel com informaćões do paciente a ser atendido
        JPanel patientInfo = new JPanel(new GridBagLayout());
        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(10, 0, 10, 0);

        JLabel patientName = new JLabel("Nome:");
        patientName.setFont(new Font("Arial", Font.PLAIN, 16));
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 0;
        patientInfo.add(patientName, gbcInfo);

        JTextField patientNameField = new JTextField();
        patientNameField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        patientNameField.setPreferredSize(new Dimension(550, 50));
        patientNameField.setEditable(false);

        gbcInfo.gridx = 1;
        gbcInfo.gridy = 0;
        patientInfo.add(patientNameField, gbcInfo);

        JLabel patientAge = new JLabel("Idade:");
        patientAge.setFont(new Font("Arial", Font.PLAIN, 16));
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 1;
        patientInfo.add(patientAge, gbcInfo);

        JTextField patientAgeField = new JTextField();
        patientAgeField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        patientAgeField.setPreferredSize(new Dimension(550, 50));
        patientAgeField.setEditable(false);

        gbcInfo.gridx = 1;
        gbcInfo.gridy = 1;
        patientInfo.add(patientAgeField, gbcInfo);


        JLabel patientPriority = new JLabel("Prioridade:");
        patientPriority.setFont(new Font("Arial", Font.PLAIN, 16));
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 2;
        patientInfo.add(patientPriority, gbcInfo);

        JTextField patientPriorityField = new JTextField();
        patientPriorityField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        patientPriorityField.setPreferredSize(new Dimension(550, 50));
        patientPriorityField.setEditable(false);

        gbcInfo.gridx = 1;
        gbcInfo.gridy = 2;
        patientInfo.add(patientPriorityField, gbcInfo);

        appointmentEvents.add(patientInfo);


        // Atender paciente
        attendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um médico");
                    return;
                }

                row = table.convertRowIndexToModel(row);

                // Médico selecionado
                String doctorName = modelTable.getValueAt(row, 1).toString();

                // Campo paciente (AGORA DIGITADO)
                JTextField patientField = new JTextField();
                patientField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                patientField.putClientProperty("JTextField.placeholderText", "Nome do paciente");
                patientField.setEditable(false);

                if (rec.getPriorityQueue().size() != 0){
                    patientField.setText(rec.getPriorityQueue().peek().getName());
                }else {
                    patientField.setText(rec.getPatientQueue().peek().getName());
                }

                JTextArea notesArea = new JTextArea(6, 25);
                notesArea.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                notesArea.setLineWrap(true);
                notesArea.setWrapStyleWord(true);

                JScrollPane notesScroll = new JScrollPane(notesArea);

                JTextField doctorField = new JTextField(doctorName);
                doctorField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                doctorField.setEditable(false);

                JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

                panel.add(new JLabel("Médico:"));
                panel.add(doctorField);

                panel.add(new JLabel("Paciente:"));
                panel.add(patientField);

                panel.add(new JLabel("Notas:"));
                panel.add(notesScroll);

                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "Iniciar Atendimento",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (result == JOptionPane.OK_OPTION) {

                    String patientName = patientField.getText();
                    String notes = notesArea.getText();
                    doc.check(patientField.getText(), String.valueOf(modelTable.getValueAt(row, 1)), notesArea.getText());

                    if (rec.getPriorityQueue().size() != 0){
                        rec.getPriorityQueue().poll();
                    }else {
                        rec.getPatientQueue().dequeue();
                    }

                    queuePanel.removePatient(rec.getIdByName(patientNameField.getText()));
                    dash.getNumberOfPatients().remove(rec.getPatientQueue().size() + rec.getPriorityQueue().size());

                    if (rec.getPatientQueue().size() == 0){
                        dash.getNextPatient().setName("Nenhum");
                    }



                    JOptionPane.showMessageDialog(null,
                            "Atendimento iniciado!\n\n" +
                                    "Médico: " + doctorName +
                                    "\nPaciente: " + patientName +
                                    "\nNotas: " + notes
                    );
                }
            }
        });

        callBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Patient p = null;

                if (rec.getPriorityQueue().size() != 0){
                    p = rec.getPriorityQueue().peek();

                }else {
                    p = rec.getPatientQueue().peek();
                }

                dash.getNextPatient().setName(p.getName());

                if (p == null) {
                    JOptionPane.showMessageDialog(null, "Fila vazia!");
                    return;
                }

                String priority;

                if (p.getPriority() != null && p.getPriority() == Priority.HIGH) {
                    priority = "URGENTE";
                    //dash.getHighPriority().remove(1);
                } else {
                    priority = "NORMAL";
                }

                patientNameField.setText(p.getName());
                patientAgeField.setText(String.valueOf(p.getAge()));
                patientPriorityField.setText(priority);
            }
        });





        gbc.gridx = 0;
        gbc.gridy = 3;
        add(appointmentEvents, gbc);



    }


}
