package views.components;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatClientProperties;

public class CardDash extends JPanel {
    private int number;
    private String text;
    private String color;
    private String name;
    private JLabel nameLabel;
    private JLabel numberLabel;

    public CardDash(String name, String text, String color){
        this.name = name;
        this.text = text;
        this.color = color;

        setLayout(new GridBagLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:16");
        setBackground(Color.decode("#eef0f2"));
        setPreferredSize(new Dimension(new Dimension(300, 200)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 10, 0);

        // Color
        JPanel topPanel = new JPanel();
        topPanel.putClientProperty(FlatClientProperties.STYLE, "arc:12");
        topPanel.setBackground(Color.decode("#" + this.color));
        topPanel.setPreferredSize(new Dimension(268, 20));

        gbc.gridx = 0;
        gbc.gridy= 0;
        add(topPanel, gbc);


        // Name
        JPanel numberPanel = new JPanel(new GridBagLayout());
        numberPanel.setBackground(Color.decode("#eef0f2"));
        numberPanel.setPreferredSize(new Dimension(200, 100));

        nameLabel = new JLabel(this.name);
        nameLabel.setForeground(Color.decode("#"+this.color));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        numberPanel.add(nameLabel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(numberPanel, gbc);

        // Text
        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.setBackground(Color.decode("#eef0f2"));
        textPanel.setPreferredSize(new Dimension(200, 45));
        JLabel textLabel = new JLabel(this.text);
        textLabel.setForeground(Color.decode("#6c757d"));
        textLabel.setFont(new Font("Arial", Font.BOLD, 20));

        textPanel.add(textLabel);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(textPanel, gbc);


    }
    public CardDash(int number, String text, String color){
        this.number = number;
        this.text = text;
        this.color = color;


        setLayout(new GridBagLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:16");
        setBackground(Color.decode("#eef0f2"));
        setPreferredSize(new Dimension(new Dimension(300, 200)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 10, 0);

        // Color
        JPanel topPanel = new JPanel();
        topPanel.putClientProperty(FlatClientProperties.STYLE, "arc:12");
        topPanel.setBackground(Color.decode("#" + this.color));
        topPanel.setPreferredSize(new Dimension(268, 20));

        gbc.gridx = 0;
        gbc.gridy= 0;
        add(topPanel, gbc);


        // Number
        JPanel numberPanel = new JPanel(new GridBagLayout());
        numberPanel.setBackground(Color.decode("#eef0f2"));
        numberPanel.setPreferredSize(new Dimension(200, 100));

        numberLabel = new JLabel(String.valueOf(this.number));
        numberLabel.setForeground(Color.decode("#"+this.color));
        numberLabel.setFont(new Font("Arial", Font.BOLD, 48));

        numberPanel.add(numberLabel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(numberPanel, gbc);

        // Text
        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.setBackground(Color.decode("#eef0f2"));
        textPanel.setPreferredSize(new Dimension(200, 45));
        JLabel textLabel = new JLabel(this.text);
        textLabel.setForeground(Color.decode("#6c757d"));
        textLabel.setFont(new Font("Arial", Font.BOLD, 20));

        textPanel.add(textLabel);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(textPanel, gbc);

    }

    public void setName(String name) {
        this.name = name;
        nameLabel.setText(name);

        revalidate();
        repaint();
    }

    public void add(int number){
        this.number =+ number;
        this.numberLabel.setText(String.valueOf(this.number));
        revalidate();
        repaint();

    }

    public void remove(int number){
        this.number--;
        this.numberLabel.setText(String.valueOf(this.number));
        revalidate();
        repaint();

    }

    public int getNumber() {
        return number;
    }
}
