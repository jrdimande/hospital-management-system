package views.recptionist;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controllers.ReceptionistController;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.DoubleLinkedList.Node;
import models.entities.Gender;
import models.entities.Patient;
import models.entities.Priority;
import services.patient.PatientRegisterRequest;

public class PatientPanel extends JPanel {
    public PatientPanel(ReceptionistController rec){
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#ffffff"));
        putClientProperty(FlatClientProperties.STYLE, "arc:16");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);

        DoubleLinkedList list = rec.getPatientList();

        // HEADER
        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.insets = new Insets(0, 25, 0, 0);

        titlePanel.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        titlePanel.setBackground(Color.decode("#eef0f2"));
        titlePanel.setPreferredSize(new Dimension(1290, 70));

        JLabel titleLabel = new JLabel("Gestão de Pacientes");
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
                "Gestão de Pacientes"
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


        // Create Table
        DefaultTableModel modelTable = new DefaultTableModel(new Object[]{"ID", "Nome", "Idade", "Sexo", "Telefone", "Endereço"}, 0){
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
        scrollPane.setPreferredSize(new Dimension(1290, 630));

        // Carregar pacientes da base de dados
        Node current = list.getHead();

        while (current != null){
            Patient p = (Patient) current.getElement();
            String gender = "";

            if (p.getGender() == Gender.MALE){
                gender = "Masculino";
            }else {
                gender = "Feminino";
            }

            modelTable.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getAge(),
                    gender,
                    p.getPhoneNumber(),
                    p.getAddress()
            });

            current = current.getNext();
        }











        gbc.gridx = 0;
        gbc.gridy = 2;
        add(scrollPane, gbc);


        // Register patient Form
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

                JTextField ageField = new JTextField();
                ageField.putClientProperty(FlatClientProperties.STYLE,"arc:16");
                ageField.putClientProperty("JTextField.placeholderText", "Idade");
                ageField.setPreferredSize(fieldSize);

                JComboBox<String> gender = new JComboBox<>();
                gender.addItem("Masculino");
                gender.addItem("Feminino");
                gender.setPreferredSize(fieldSize);

                JTextField phoneField = new JTextField();
                phoneField.putClientProperty(FlatClientProperties.STYLE,"arc:16");
                phoneField.putClientProperty("JTextField.placeholderText", "Telefone");
                phoneField.setPreferredSize(fieldSize);

                JTextField addrField = new JTextField();
                addrField.putClientProperty(FlatClientProperties.STYLE,"arc:16");
                addrField.putClientProperty("JTextField.placeholderText", "Endereço");
                addrField.setPreferredSize(fieldSize);

                // Nome
                gbcForm.gridx = 0;
                gbcForm.gridy = 0;
                registerForm.add(new JLabel("Nome:"), gbcForm);
                gbcForm.gridx = 1;
                registerForm.add(nameField, gbcForm);

                // Idade
                gbcForm.gridx = 0;
                gbcForm.gridy = 1;
                registerForm.add(new JLabel("Idade:"), gbcForm);
                gbcForm.gridx = 1;
                registerForm.add(ageField, gbcForm);

                // Sexo
                gbcForm.gridx = 0;
                gbcForm.gridy = 2;
                registerForm.add(new JLabel("Sexo:"), gbcForm);
                gbcForm.gridx = 1;
                registerForm.add(gender, gbcForm);

                // Telefone
                gbcForm.gridx = 0;
                gbcForm.gridy = 3;
                registerForm.add(new JLabel("Telefone:"), gbcForm);
                gbcForm.gridx = 1;
                registerForm.add(phoneField, gbcForm);

                // Endereço
                gbcForm.gridx = 0;
                gbcForm.gridy = 4;
                registerForm.add(new JLabel("Endereço:"), gbcForm);
                gbcForm.gridx = 1;
                registerForm.add(addrField, gbcForm);

                JButton saveBtn = new JButton("Salvar");
                saveBtn.setBackground(Color.decode("#0466c8"));
                saveBtn.setForeground(Color.WHITE);
                saveBtn.setPreferredSize(new Dimension(300, 35));

                gbcForm.gridx = 0;
                gbcForm.gridy = 5;
                gbcForm.gridwidth = 2;
                registerForm.add(saveBtn, gbcForm);

                JDialog dialog = new JDialog((Frame) null, "Registrar Paciente", true);
                dialog.setSize(500, 450);
                dialog.setLocationRelativeTo(null);
                dialog.setContentPane(registerForm);

                saveBtn.addActionListener(ev -> {

                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String sex = gender.getSelectedItem().toString();
                    String phone = phoneField.getText();
                    String addr = addrField.getText();



                    if (name.isEmpty()  || phone.isEmpty() || addr.isEmpty() || String.valueOf(age).isEmpty()) {
                        JOptionPane.showMessageDialog(dialog, "Preencha todos os campos!");
                        return;
                    }

                    PatientRegisterRequest p = new PatientRegisterRequest();
                    p.setName(name);
                    p.setAge(age);
                    p.setPhoneNumber(phone);

                    if (sex == "Masculino"){
                        p.setGender(Gender.MALE);
                    }else {
                        p.setGender(Gender.FEMALE);
                    }

                    p.setAddress(addr);

                    rec.registerPatient(p);

                    modelTable.addRow(new Object[]{
                            p.getId(),
                            name,
                            age,
                            sex,
                            phone,
                            addr
                    });

                    JOptionPane.showMessageDialog(dialog, "Paciente registado com sucesso!");
                    dialog.dispose();
                });

                dialog.setVisible(true);
            }
        });


        // Update Patient
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int viewRow = table.getSelectedRow();

                if (viewRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um paciente!");
                    return;
                }

                int row = table.convertRowIndexToModel(viewRow);

                // pegar dados da tabela
                String id = modelTable.getValueAt(row, 0).toString();
                String name = modelTable.getValueAt(row, 1).toString();
                String age = modelTable.getValueAt(row, 2).toString();
                String sex = modelTable.getValueAt(row, 3).toString();
                String phone = modelTable.getValueAt(row, 4).toString();
                String addr = modelTable.getValueAt(row, 5).toString();



                // FORM
                JPanel form = new JPanel(new GridBagLayout());
                GridBagConstraints gbcForm = new GridBagConstraints();
                gbcForm.insets = new Insets(8, 8, 8, 8);
                gbcForm.fill = GridBagConstraints.HORIZONTAL;
                gbcForm.weightx = 1;

                Dimension fieldSize = new Dimension(300, 30);

                JTextField nameField = new JTextField(name);
                nameField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                nameField.setPreferredSize(fieldSize);

                JTextField ageField = new JTextField(age);
                ageField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                ageField.setPreferredSize(fieldSize);

                JComboBox<String> gender = new JComboBox<>();
                gender.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                gender.addItem("Masculino");
                gender.addItem("Feminino");
                gender.setSelectedItem(sex);
                gender.setPreferredSize(fieldSize);

                JTextField phoneField = new JTextField(phone);
                phoneField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                phoneField.setPreferredSize(fieldSize);

                JTextField addrField = new JTextField(addr);
                addrField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
                addrField.setPreferredSize(fieldSize);

                // Nome
                gbcForm.gridx = 0;
                gbcForm.gridy = 0;
                form.add(new JLabel("Nome:"), gbcForm);
                gbcForm.gridx = 1;
                form.add(nameField, gbcForm);

                // Idade
                gbcForm.gridx = 0;
                gbcForm.gridy = 1;
                form.add(new JLabel("Idade:"), gbcForm);
                gbcForm.gridx = 1;
                form.add(ageField, gbcForm);

                // Sexo
                gbcForm.gridx = 0;
                gbcForm.gridy = 2;
                form.add(new JLabel("Sexo:"), gbcForm);
                gbcForm.gridx = 1;
                form.add(gender, gbcForm);

                // Telefone
                gbcForm.gridx = 0;
                gbcForm.gridy = 3;
                form.add(new JLabel("Telefone:"), gbcForm);
                gbcForm.gridx = 1;
                form.add(phoneField, gbcForm);

                // Endereço
                gbcForm.gridx = 0;
                gbcForm.gridy = 4;
                form.add(new JLabel("Endereço:"), gbcForm);
                gbcForm.gridx = 1;
                form.add(addrField, gbcForm);

                JButton saveBtn = new JButton("Atualizar");
                saveBtn.setBackground(Color.decode("#f56416"));
                saveBtn.setForeground(Color.WHITE);
                saveBtn.setPreferredSize(new Dimension(300, 35));

                gbcForm.gridx = 0;
                gbcForm.gridy = 5;
                gbcForm.gridwidth = 2;
                form.add(saveBtn, gbcForm);

                JDialog dialog = new JDialog((Frame) null, "Atualizar Paciente", true);
                dialog.setSize(500, 350);
                dialog.setLocationRelativeTo(null);
                dialog.setContentPane(form);


                saveBtn.addActionListener(ev -> {

                    modelTable.setValueAt(nameField.getText(), row, 1);
                    modelTable.setValueAt(ageField.getText(), row, 2);
                    modelTable.setValueAt(gender.getSelectedItem().toString(), row, 3);
                    modelTable.setValueAt(phoneField.getText(), row, 4);
                    modelTable.setValueAt(addrField.getText(), row, 5);

                    Gender g;

                    if (gender.equals("Masculino")){
                        g = Gender.MALE;
                    }else {
                        g = Gender.FEMALE;
                    }

                    Patient p = new Patient(nameField.getText(),
                            Integer.parseInt(ageField.getText()),
                            g,
                            phoneField.getText(),
                            addrField.getText()
                            );
                    p.setId((int)modelTable.getValueAt(row, 0));

                    rec.updatePatient(p);


                    JOptionPane.showMessageDialog(dialog, "Paciente atualizado com sucesso!");
                    dialog.dispose();
                });

                dialog.setVisible(true);
            }
        });

        // Remove client
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                row = table.convertRowIndexToModel(row);

                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um paciente para remover!");
                    return;
                }

                String nome = modelTable.getValueAt(row, 1).toString();

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Tens certeza que quer remover o paciente: " + nome + "?",
                        "Confirmar Remoção",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    rec.removePatient((int) modelTable.getValueAt(row, 0));
                    modelTable.removeRow(row);
                    JOptionPane.showMessageDialog(null, "Paciente removido com sucesso!");
                }
            }
        });

        addToQueueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if (row == -1){
                    JOptionPane.showMessageDialog(null, "Selecione um paciente para adicionar na fila!");
                    return;
                }

                JPanel priorityPanel = new JPanel(new GridBagLayout());
                priorityPanel.setPreferredSize(new Dimension(400, 400));
                GridBagConstraints gbcPriority = new GridBagConstraints();
                gbcPriority.insets = new Insets(5, 5,5,5);


                JComboBox<String> combo = new JComboBox<>();
                combo.setPreferredSize(new Dimension(300, 40));
                combo.addItem("NORMAL");
                combo.addItem("URGENTE");

                JButton addBtn = new JButton("Adicionar");
                addBtn.setForeground(Color.WHITE);
                addBtn.setBackground(Color.decode("#069e2d"));
                addBtn.setFont(new Font("Arial", Font.BOLD, 16));
                addBtn.setPreferredSize(new Dimension(400, 40));

                gbcPriority.gridx = 0;
                gbcPriority.gridy = 0;
                priorityPanel.add(new JLabel("Prioridade"), gbcPriority);
                gbcPriority.gridx = 1;
                priorityPanel.add(combo, gbcPriority);

                gbcPriority.gridwidth = 2;
                gbcPriority.gridy = 1;
                priorityPanel.add(addBtn, gbcPriority);

                JDialog dialog = new JDialog((Frame) null, "Adicionar na Fila");
                dialog.setSize(new Dimension(400, 200));
                dialog.setLocationRelativeTo(null);
                dialog.setContentPane(priorityPanel);

                addBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Priority p;
                        if (combo.getSelectedItem().equals("NORMAL")){
                            p = Priority.LOW;
                        }else {
                            p = Priority.HIGH;
                        }
                        rec.addPacientToQueue((int)modelTable.getValueAt(row, 0), p);
                        JOptionPane.showMessageDialog(dialog, "Paciente adicionado com sucesso!");
                        dialog.dispose();


                    }
                });
                dialog.setVisible(true);



            }
        });




    }
}
