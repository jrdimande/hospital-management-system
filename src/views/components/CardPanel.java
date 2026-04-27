package views.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CardPanel extends JPanel {

    private JLabel titleLabel;
    private JLabel valueLabel;
    private JLabel descriptionLabel;
    private JLabel iconLabel;

    public CardPanel(String title,
                     String value,
                     String description,
                     String svgPath,
                     String bgColor) {

        setLayout(new BorderLayout());
        setBackground(Color.decode(bgColor));
        setPreferredSize(new Dimension(550, 250));

        putClientProperty(
                FlatClientProperties.STYLE,
                "arc:20;bg:" + bgColor
        );

        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        setBorder(padding);


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SF Pro Display", Font.BOLD, 16));
        titleLabel.setForeground(new Color(33, 33, 33));


        iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        if (svgPath != null) {
            FlatSVGIcon icon = new FlatSVGIcon(svgPath, 24, 24);
            iconLabel.setIcon(icon);
        }

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(iconLabel, BorderLayout.EAST);


        valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("SF Pro Display", Font.BOLD, 24));
        valueLabel.setForeground(new Color(33, 33, 33));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);


        descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 12));
        descriptionLabel.setForeground(new Color(50, 50, 50));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(valueLabel, BorderLayout.CENTER);
        add(descriptionLabel, BorderLayout.SOUTH);
    }


    public void setValue(String value) {
        valueLabel.setText(value);
    }


    public void setTitle(String title) {
        titleLabel.setText(title);
    }


    public void setDescription(String description) {
        descriptionLabel.setText(description);
    }


    public void setIcon(String svgPath) {
        FlatSVGIcon icon = new FlatSVGIcon(svgPath, 24, 24);
        iconLabel.setIcon(icon);
    }
}