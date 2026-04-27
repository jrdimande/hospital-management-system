package views.home;

import controllers.AppointmentController;
import models.data_structures.Stack.Stack;
import models.entities.Appointment;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HistoryPanel extends JPanel {

    private AppointmentController appointmentController;
    private DefaultTableModel model;
    private JTable table;
    private JTextField searchField;
    private JButton refreshButton;
    private JButton deleteButton;
    private JButton exportButton;

    public HistoryPanel() {
        appointmentController = new AppointmentController();
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#f8f9fa"));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        initComponents();
        loadAppointments();
    }

    private void initComponents() {
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(Color.decode("#eef0f2"));
        headerPanel.putClientProperty(FlatClientProperties.STYLE, "arc:12");
        headerPanel.setPreferredSize(new Dimension(1290, 56));
        GridBagConstraints gbcH = new GridBagConstraints();
        gbcH.insets = new Insets(0, 20, 0, 0);
        gbcH.anchor = GridBagConstraints.WEST;
        gbcH.fill = GridBagConstraints.HORIZONTAL;
        gbcH.weightx = 1;

        JLabel titleLabel = new JLabel("Histórico de Consultas");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.decode("#0d1b2a"));
        headerPanel.add(titleLabel, gbcH);


        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 10));
        toolbar.setBackground(Color.WHITE);
        toolbar.putClientProperty(FlatClientProperties.STYLE, "arc:12");

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode("#0077b6"), 1),
                "Gestão de Histórico"
        );
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitleColor(Color.decode("#0077b6"));
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 12));
        toolbar.setBorder(border);

        refreshButton = createButton("Atualizar",        "#0077b6");
        deleteButton  = createButton("Apagar Histórico", "#ea2b1f");
        exportButton  = createButton("Exportar",         "#f56416");

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 32));
        searchField.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        searchField.putClientProperty("JTextField.placeholderText", "Pesquisar...");

        JComboBox<String> filterCombo = new JComboBox<>(new String[]{"Todos", "Paciente", "Médico"});
        filterCombo.setPreferredSize(new Dimension(120, 32));
        filterCombo.putClientProperty(FlatClientProperties.STYLE, "arc:8");

        toolbar.add(refreshButton);
        toolbar.add(deleteButton);
        toolbar.add(exportButton);

        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setPreferredSize(new Dimension(1, 28));
        sep.setForeground(Color.decode("#dee2e6"));
        toolbar.add(sep);

        toolbar.add(searchField);
        toolbar.add(filterCombo);


        model = new DefaultTableModel(
                new Object[]{"ID", "Paciente", "Médico", "Data", "Notas"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);

        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setRowHeight(36);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(Color.decode("#d0ebff"));
        table.setSelectionForeground(Color.decode("#0d1b2a"));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.putClientProperty(FlatClientProperties.STYLE, "showHorizontalLines:true; rowHeight:36");

        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(Color.decode("#0077b6"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE,
                "background:#0077b6; foreground:#ffffff");

        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : Color.decode("#f1f5f9"));
                }
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#dee2e6"), 1));
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "arc:10");

        refreshButton.addActionListener(e -> loadAppointments());

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = searchField.getText().trim();
                String selected = filterCombo.getSelectedItem().toString();
                if (text.isEmpty()) { sorter.setRowFilter(null); return; }
                int col = -1;
                if (selected.equals("Paciente")) col = 1;
                else if (selected.equals("Médico")) col = 2;
                if (col == -1) sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                else           sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, col));
            }
        });

        // deleteButton.addActionListener(e -> { });
        // exportButton.addActionListener(e -> { });

        JPanel topWrapper = new JPanel(new BorderLayout(0, 8));
        topWrapper.setBackground(Color.decode("#f8f9fa"));
        topWrapper.add(headerPanel, BorderLayout.NORTH);
        topWrapper.add(toolbar,     BorderLayout.SOUTH);

        add(topWrapper,  BorderLayout.NORTH);
        add(scrollPane,  BorderLayout.CENTER);
    }

    private JButton createButton(String text, String hex) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.decode(hex));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(170, 32));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.putClientProperty(FlatClientProperties.STYLE,
                "arc:16; background:" + hex + "; foreground:#ffffff");
        return btn;
    }

    private void loadAppointments() {
        model.setRowCount(0);
        try {
            Stack stack = appointmentController.getAppointments();
            if (stack == null || stack.isEmpty()) return;
            Stack temp = new Stack();
            while (!stack.isEmpty()) {
                Appointment a = (Appointment) stack.peek();
                stack.pop();
                model.addRow(new Object[]{
                        a.getId(),
                        a.getPatient(),
                        a.getDoctor(),
                        a.getDate(),
                        a.getNotes()
                });
                temp.push(a);
            }
            while (!temp.isEmpty()) {
                stack.push(temp.peek());
                temp.pop();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar histórico", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}