package views.components;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatClientProperties;

public class Card extends JPanel {
    private int position;
    private int id;
    private String priority;
    private String name;

    public Card(int id, int position, String name, String priority){
        this.position = position;
        this.id = id;
        this.name = name;
        this.priority = priority;


        setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        setMinimumSize(new Dimension(900, 90));
        putClientProperty(FlatClientProperties.STYLE, "arc:16");
        setBackground(Color.decode("#ffffff"));
        setLayout(new GridBagLayout());
        //setBorder(BorderFactory.createLineBorder(Color.GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
                                                                                                                       
        gbc.insets = new Insets(10, 400, 10, 300);

        // ID Visualization
        JPanel idNamePanel = new JPanel(new FlowLayout());
        idNamePanel.setBackground(Color.decode("#ffffff"));
        idNamePanel.setPreferredSize(new Dimension(300, 90));

        JPanel idPanel = new JPanel(new GridBagLayout());
        idPanel.setBackground(Color.decode("#ffffff"));
        idPanel.setPreferredSize(new Dimension(75, 75));
        idPanel.putClientProperty(FlatClientProperties.STYLE, "arc:360");

        JLabel idLabel = new JLabel(String.valueOf(position));
        idLabel.setFont(new Font("Arial", Font.BOLD, 30));
        idPanel.add(idLabel);

        idNamePanel.add(idPanel);


        // Name Visualization
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground(Color.decode("#ffffff"));
        namePanel.setPreferredSize(new Dimension(200, 40));

        JLabel nameLabel = new JLabel(this.name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        namePanel.add(nameLabel, BorderLayout.NORTH);

        idNamePanel.add(namePanel);
        add(idNamePanel);setLayout(new GridBagLayout());


        // Priority Visualization
        JPanel priorityPanel = new JPanel(new GridBagLayout());
        priorityPanel.setPreferredSize(new Dimension(240, 55));

        JLabel priorityLabel = new JLabel(this.priority);
        priorityLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priorityPanel.add(priorityLabel);

        if (this.priority.equals("NORMAL"))  {
            idPanel.setBackground(Color.decode("#1a7431"));
            idLabel.setForeground(Color.WHITE);
            priorityPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#208b3a")));
            priorityPanel.setBackground(Color.decode("#b7efc5"));
            priorityLabel.setForeground(Color.decode("#208b3a"));

        }else if (this.priority.equals("URGENTE")){
                idPanel.setBackground(Color.decode("#db222a"));
                idLabel.setForeground(Color.WHITE);
                priorityPanel.setBackground(Color.decode("#f6cacc"));
                priorityLabel.setForeground(Color.decode("#d02224"));
                priorityPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#d02224")));
        }else {
            idPanel.setBackground(Color.decode("#f54703"));
            idLabel.setForeground(Color.WHITE);
            priorityPanel.setBackground(Color.decode("#fde9c3"));
            priorityLabel.setForeground(Color.decode("#f54703"));
            priorityPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#f54703")));
        }


        add(priorityPanel, gbc);



    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
