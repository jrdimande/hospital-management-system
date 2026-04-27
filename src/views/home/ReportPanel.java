package views.home;

import javax.swing.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import views.components.CardPanel;

import java.awt.*;

public class ReportPanel extends JPanel {
    CardPanel card1, card2, card3, card4;

    public ReportPanel(){
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#ffffff"));
        putClientProperty(FlatClientProperties.STYLE, "arc:16");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);


        // HEADER
        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.insets = new Insets(0, 25, 0, 0);

        titlePanel.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        titlePanel.setBackground(Color.decode("#eef0f2"));
        titlePanel.setPreferredSize(new Dimension(1290, 70));

        JLabel titleLabel = new JLabel("Relatórios & Histórico");
        titleLabel.setForeground(Color.decode("#0d1b2a"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;

        gbcHeader.anchor = GridBagConstraints.WEST;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;
        gbcHeader.weightx = 1;

        titlePanel.add(titleLabel, gbcHeader);

        add(titlePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        // CARDS PANEL
        JPanel cardsPanel = new JPanel(new GridBagLayout());
        cardsPanel.putClientProperty(FlatClientProperties.STYLE, "arc:16");
        cardsPanel.setPreferredSize(new Dimension(1290, 600));
        GridBagConstraints gbcCards = new GridBagConstraints();
        gbcCards.insets = new Insets(15, 46, 15, 46);

        card1 = new CardPanel("Total atendidos", "150", "Este mês","views/assets/sidebar/patient.svg","#eef0f2");
        gbcCards.gridx = 0;
        gbcCards.gridy = 0;
        cardsPanel.add(card1, gbcCards);

        card2 = new CardPanel("Total atendidos", "150", "Este mês","views/assets/sidebar/patient.svg","#eef0f2");
        gbcCards.gridx = 1;
        gbcCards.gridy = 0;
        cardsPanel.add(card2, gbcCards);

        card3 = new CardPanel("Total atendidos", "150", "Este mês","views/assets/sidebar/patient.svg","#eef0f2");
        gbcCards.gridx = 0;
        gbcCards.gridy = 1;
        cardsPanel.add(card3, gbcCards);

        card4 = new CardPanel("Total atendidos", "150", "Este mês","views/assets/sidebar/patient.svg","#eef0f2");
        gbcCards.gridx = 1;
        gbcCards.gridy = 1;
        cardsPanel.add(card4, gbcCards);
        add(cardsPanel, gbc);

        // Buttons Panel
        JPanel buttosPanel = new JPanel(new GridBagLayout());
        buttosPanel.setBackground(Color.WHITE);
        buttosPanel.setPreferredSize(new Dimension(1290, 100));
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.insets = new Insets(10, 15, 10, 15);

        FlatSVGIcon excelIcon = new FlatSVGIcon("views/assets/report/excel.svg", 35, 35);
        excelIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon printerIcon = new FlatSVGIcon("views/assets/report/printer.svg", 35, 35);
        printerIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        FlatSVGIcon pdfIcon = new FlatSVGIcon("views/assets/report/pdf.svg", 35, 35);
        pdfIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));

        JButton excelBtn = new JButton("Exportar Excel", excelIcon);
        excelBtn.setBackground(Color.decode("#208b3a"));
        excelBtn.setForeground(Color.WHITE);
        excelBtn.setFont(new Font("Arial", Font.BOLD, 16));
        excelBtn.setPreferredSize(new Dimension(380, 50));

        gbcButtons.gridx = 0;
        gbcButtons.gridy = 0;
        buttosPanel.add(excelBtn, gbcButtons);



        JButton printBtn = new JButton("Imprimir", printerIcon);
        printBtn.setForeground(Color.WHITE);
        printBtn.setBackground(Color.decode("#0077b6"));
        printBtn.setFont(new Font("Arial", Font.BOLD, 16));
        printBtn.setPreferredSize(new Dimension(380, 50));

        gbcButtons.gridx = 1;
        gbcButtons.gridy = 0;
        buttosPanel.add(printBtn, gbcButtons);




        JButton pdfBtn =  new JButton("Exportar PDF", pdfIcon);
        pdfBtn.setForeground(Color.WHITE);
        pdfBtn.setBackground(Color.decode("#e01e37"));
        pdfBtn.setFont(new Font("Arial", Font.BOLD, 16));
        pdfBtn.setPreferredSize(new Dimension(380, 50));

        gbcButtons.gridx = 2;
        gbcButtons.gridy = 0;
        buttosPanel.add(pdfBtn, gbcButtons);

        gbc.gridy = 2;
        add(buttosPanel, gbc);






    }
}
